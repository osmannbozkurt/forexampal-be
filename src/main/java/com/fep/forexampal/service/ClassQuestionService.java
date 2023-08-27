package com.fep.forexampal.service;

import com.fep.forexampal.common.utils.PagingHelper;
import com.fep.forexampal.dto.ClassFilterOptions;
import com.fep.forexampal.dto.ClassQuestionAddRequestDto;
import com.fep.forexampal.dto.ClassQuestionAnswerAddRequestDto;
import com.fep.forexampal.dto.ClassQuestionAnswerDto;
import com.fep.forexampal.dto.ClassQuestionDetailResponseDto;
import com.fep.forexampal.dto.ClassQuestionItemDto;
import com.fep.forexampal.dto.ClassQuestionResponseDto;
import com.fep.forexampal.exception.ClassQuestionAnswerNotFoundException;
import com.fep.forexampal.exception.ClassQuestionNotFoundException;
import com.fep.forexampal.exception.SystemException;
import com.fep.forexampal.mapper.ClassQuestionAnswerMapper;
import com.fep.forexampal.mapper.ClassQuestionMapper;
import com.fep.forexampal.persistence.entity.ClassQuestion;
import com.fep.forexampal.persistence.entity.ClassQuestionAnswer;
import com.fep.forexampal.persistence.entity.Image;
import com.fep.forexampal.persistence.entity.Subject;
import com.fep.forexampal.persistence.entity.User;
import com.fep.forexampal.persistence.repository.ClassQuestionAnswerRepository;
import com.fep.forexampal.persistence.repository.ClassQuestionRepository;
import com.fep.forexampal.service.media.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static com.fep.forexampal.common.enums.ErrorMessage.CLASS_QUESTION_ANSWER_NOT_FOUND_ERROR;
import static com.fep.forexampal.common.enums.ErrorMessage.CLASS_QUESTION_NOT_FOUND_ERROR;
import static com.fep.forexampal.common.enums.ErrorMessage.SYSTEM_ERROR;

@Service
@RequiredArgsConstructor
public class ClassQuestionService {

    private final UserService userService;
    private final SubjectService subjectService;
    private final ClassQuestionMapper classQuestionMapper;
    private final ClassQuestionAnswerMapper classQuestionAnswerMapper;
    private final ImageService imageService;
    private final ClassQuestionRepository classQuestionRepository;
    private final ClassQuestionAnswerRepository classQuestionAnswerRepository;
    private final ClassQuestionSpecification classQuestionSpecification;

    public ClassQuestionResponseDto findAll(ClassFilterOptions options, Pageable pageable) {
        Specification<ClassQuestion> specifications = classQuestionSpecification.generateFilterSpecs(options);
        Page<ClassQuestion> pagedClassQuestions = classQuestionRepository.findAll(specifications, pageable);
        List<ClassQuestionItemDto> classQuestionItemList = classQuestionMapper.toClassQuestionItemList(pagedClassQuestions.getContent());
        return new ClassQuestionResponseDto(classQuestionItemList, PagingHelper.build(pagedClassQuestions));
    }

    @Transactional
    public void saveQuestion(Long studentId, ClassQuestionAddRequestDto requestDto, MultipartFile file) {
        User questionOwner = userService.findById(studentId);
        Subject subject = subjectService.findById(requestDto.getSubjectId());
        List<User> taggedUsers = userService.getUserListByIds(requestDto.getTaggedUsers());
        ClassQuestion classQuestion = classQuestionMapper.toClassQuestion(requestDto);
        classQuestion.setUser(questionOwner);
        classQuestion.setSubject(subject);

        if (CollectionUtils.isNotEmpty(taggedUsers)) {
            classQuestion.setTaggedUser(new HashSet<>(taggedUsers));
        }

        Image image = checkAndBuildImageContainer(file);
        classQuestion.setImage(image);

        classQuestionRepository.save(classQuestion);
    }

    @Transactional
    public void addAnswerToClasQuestion(Long classQuestionId, ClassQuestionAnswerAddRequestDto request, MultipartFile file) {
        ClassQuestion classQuestion = classQuestionRepository.findById(classQuestionId).orElseThrow(() ->
                new ClassQuestionNotFoundException(CLASS_QUESTION_NOT_FOUND_ERROR.getMessage(), CLASS_QUESTION_NOT_FOUND_ERROR.getCode(), classQuestionId.toString()));

        ClassQuestionAnswer classQuestionAnswer = classQuestionAnswerMapper.toClassQuestionAnswer(request);

        if (Objects.nonNull(request.getParentAnswerId())) {
            ClassQuestionAnswer parentAnswer = classQuestionAnswerRepository.findById(request.getParentAnswerId()).orElseThrow(() ->
                    new ClassQuestionNotFoundException(CLASS_QUESTION_NOT_FOUND_ERROR.getMessage(), CLASS_QUESTION_NOT_FOUND_ERROR.getCode(), request.getParentAnswerId().toString()));
            classQuestionAnswer.setParent(parentAnswer);
        }

        User user = userService.findById(request.getAnswerOwnerUser());
        classQuestionAnswer.setUser(user);

        if (CollectionUtils.isNotEmpty(request.getTaggedUsers())) {
            List<User> taggedUsers = userService.getUserListByIds(request.getTaggedUsers());
            classQuestionAnswer.setTaggedUsers(new HashSet<>(taggedUsers));
        }

        if (Objects.nonNull(request.getParentAnswerId())) {
            ClassQuestionAnswer parentAnswer = classQuestionAnswerRepository.findById(request.getParentAnswerId()).orElse(null);
            classQuestionAnswer.setParent(parentAnswer);
        }

        Image image = checkAndBuildImageContainer(file);
        classQuestionAnswer.setImage(image);

        classQuestionAnswer.setClassQuestion(classQuestion);

        classQuestionAnswerRepository.save(classQuestionAnswer);
    }

    public ClassQuestionDetailResponseDto getDetail(Long id, Pageable pageable) {
        ClassQuestion classQuestion = classQuestionRepository.findById(id).orElseThrow(() ->
                new ClassQuestionNotFoundException(CLASS_QUESTION_NOT_FOUND_ERROR.getMessage(), CLASS_QUESTION_NOT_FOUND_ERROR.getCode(), id.toString()));
        ClassQuestionItemDto classQuestionDto = classQuestionMapper.toClassQuestionDto(classQuestion);

        Page<ClassQuestionAnswer> parentAnswers = classQuestionAnswerRepository
                .findAllByClassQuestionIdAndParentIdIsNullOrderByCreatedDateAsc(classQuestion.getId(), pageable);
        List<ClassQuestionAnswerDto> parentAnswerDtoList = classQuestionAnswerMapper.toClassQuestionAnswerDtoList(parentAnswers.getContent());
        parentAnswerDtoList.forEach(classQuestionAnswerDto -> {
            List<ClassQuestionAnswer> classChildAnswers = classQuestionAnswerRepository.findAllByParentIdOrderByCreatedDateAsc(classQuestionAnswerDto.getId());
            List<ClassQuestionAnswerDto> classQuestionAnswerDtoList = classQuestionAnswerMapper.toClassQuestionAnswerDtoList(classChildAnswers);
            classQuestionAnswerDto.setChildAnswers(classQuestionAnswerDtoList);
        });

        return new ClassQuestionDetailResponseDto(classQuestionDto, parentAnswerDtoList, PagingHelper.build(parentAnswers));
    }

    @Transactional
    public void markAnswerAskCorrect(Long id, Long answerId) {
        ClassQuestion classQuestion = classQuestionRepository.findById(id).orElseThrow(() ->
                new ClassQuestionNotFoundException(CLASS_QUESTION_NOT_FOUND_ERROR.getMessage(), CLASS_QUESTION_NOT_FOUND_ERROR.getCode(), id.toString()));

        ClassQuestionAnswer classQuestionAnswer = classQuestionAnswerRepository.findById(answerId).orElseThrow(() ->
                new ClassQuestionAnswerNotFoundException(CLASS_QUESTION_ANSWER_NOT_FOUND_ERROR.getMessage(), CLASS_QUESTION_ANSWER_NOT_FOUND_ERROR.getCode(), id.toString()));

        classQuestionAnswer.setCorrect(true);
        classQuestionAnswerRepository.save(classQuestionAnswer);

        classQuestion.setSolved(true);
        classQuestionRepository.save(classQuestion);
    }

    private Image checkAndBuildImageContainer(MultipartFile file) {
        Image image = new Image();
        if (Objects.isNull(file)) {
            return image;
        }

        try {
            String path = imageService.uploadAndGetPath(file);
            image.setImagePath(path);
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            image.setWidth(bufferedImage.getWidth());
            image.setHeight(bufferedImage.getHeight());
        } catch (IOException e) {
            // delete created image
            throw new SystemException(SYSTEM_ERROR.getMessage(), SYSTEM_ERROR.getCode());
        }
        return image;
    }

}
