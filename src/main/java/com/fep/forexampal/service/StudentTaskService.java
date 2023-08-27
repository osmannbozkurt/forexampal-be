package com.fep.forexampal.service;

import com.fep.forexampal.common.enums.UserType;
import com.fep.forexampal.common.utils.DateUtils;
import com.fep.forexampal.common.utils.MediaUtils;
import com.fep.forexampal.dto.StudentTaskAnswerDto;
import com.fep.forexampal.dto.StudentTaskAnswerRequestDto;
import com.fep.forexampal.dto.StudentTaskDetailResponseDto;
import com.fep.forexampal.dto.StudentTaskDto;
import com.fep.forexampal.dto.StudentTaskItemDto;
import com.fep.forexampal.dto.StudentTaskResponseDto;
import com.fep.forexampal.exception.StudentTaskNotFoundException;
import com.fep.forexampal.exception.UserNotFoundException;
import com.fep.forexampal.mapper.StudentTaskMapper;
import com.fep.forexampal.persistence.entity.Student;
import com.fep.forexampal.persistence.entity.StudentTask;
import com.fep.forexampal.persistence.entity.StudentTaskAnswer;
import com.fep.forexampal.persistence.entity.Teacher;
import com.fep.forexampal.persistence.repository.StudentRepository;
import com.fep.forexampal.persistence.repository.StudentTaskAnswerRepository;
import com.fep.forexampal.persistence.repository.StudentTaskRepository;
import com.fep.forexampal.persistence.repository.TeacherRepository;
import com.fep.forexampal.service.media.DocumentService;
import com.fep.forexampal.service.media.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.fep.forexampal.common.enums.ErrorMessage.STUDENT_TASK_NOT_FOUND;
import static com.fep.forexampal.common.enums.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StudentTaskService {

    private final StudentTaskRepository studentTaskRepository;
    private final StudentTaskMapper studentTaskMapper;
    private final ImageService imageService;
    private final DocumentService documentService;
    private final StudentTaskAnswerRepository studentTaskAnswerRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentTaskResponseDto getAllByStudentId(Long studentId) {
        List<StudentTask> studentTasks = studentTaskRepository.findAllByStudentId(studentId);
        List<StudentTaskDto> studentTaskDtoList = studentTaskMapper.toStudentTaskDtoList(studentTasks);
        List<StudentTaskItemDto> itemDtoList = populateToItemDtoList(studentTaskDtoList);
        return new StudentTaskResponseDto(itemDtoList);
    }

    @Transactional
    public void saveStudentTaskAnswer(StudentTaskAnswerRequestDto taskAnswer, MultipartFile file) throws IOException {
        StudentTask studentTask = studentTaskRepository.findById(taskAnswer.getStudentTaskId()).orElseThrow(() ->
                new StudentTaskNotFoundException(STUDENT_TASK_NOT_FOUND.getMessage(), STUDENT_TASK_NOT_FOUND.getCode(),
                        taskAnswer.getStudentTaskId().toString()));

        StudentTaskAnswer studentTaskAnswer = studentTaskMapper.toStudentTaskAnswer(taskAnswer);
        studentTaskAnswer.setStudentTask(studentTask);
        studentTaskAnswer.setAnswerDate(new Date());

        if (Objects.nonNull(file)) {
            setMediaPart(studentTaskAnswer, file);
        }

        studentTaskAnswerRepository.save(studentTaskAnswer);
    }

    public StudentTaskDetailResponseDto getDetailById(Long id) {
        StudentTask studentTask = studentTaskRepository.findById(id).orElseThrow(() ->
                new StudentTaskNotFoundException(STUDENT_TASK_NOT_FOUND.getMessage(), STUDENT_TASK_NOT_FOUND.getCode(), id.toString()));

        StudentTaskDetailResponseDto detailResponse = studentTaskMapper.toDetailResponseDto(studentTask);

        List<StudentTaskAnswer> studentTaskAnswers = studentTaskAnswerRepository.findAllByStudentTaskIdOrderByAnswerDateAsc(studentTask.getId());

        List<StudentTaskAnswerDto> studentTaskAnswerDtoList = studentTaskAnswers.stream().map(studentTaskAnswer -> {
            StudentTaskAnswerDto studentTaskAnswerDto = studentTaskMapper.toStudentTaskAnswerDto(studentTaskAnswer);
            populateForUserInfo(studentTaskAnswerDto, studentTaskAnswer);
            return studentTaskAnswerDto;
        }).toList();

        detailResponse.setAnswers(studentTaskAnswerDtoList);
        return detailResponse;
    }

    private void setMediaPart(StudentTaskAnswer studentTaskAnswer, MultipartFile file) throws IOException {
        String imagePath;
        if (MediaUtils.isImage(file.getOriginalFilename())) {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            studentTaskAnswer.setWidth(bufferedImage.getWidth());
            studentTaskAnswer.setHeight(bufferedImage.getHeight());
            imagePath = imageService.uploadAndGetPath(file);
        } else {
            imagePath = documentService.uploadAndGetPath(file);
        }
        studentTaskAnswer.setImagePath(imagePath);
    }

    private List<StudentTaskItemDto> populateToItemDtoList(List<StudentTaskDto> studentTaskDtoList)  {
        Map<Date, List<StudentTaskDto>> groupedTasks = studentTaskDtoList
                .stream()
                .collect(Collectors.groupingBy(StudentTaskDto::getStartDay));
        return groupedTasks.keySet().stream().map(startDate -> StudentTaskItemDto.builder()
                .startMonthAsName(DateUtils.getMonthAsName(startDate))
                .startDay(DateUtils.getDayOfWeekAsName(startDate))
                .studentTasks(groupedTasks.get(startDate))
                .build()).toList();
    }

    private void populateForUserInfo(StudentTaskAnswerDto studentTaskAnswerDto, StudentTaskAnswer taskAnswer) {
        UserType userType = taskAnswer.getUserType();
        if (UserType.STUDENT == userType) {
            Student student = studentRepository.findById(taskAnswer.getUserId()).orElseThrow(() ->
                    new UserNotFoundException(USER_NOT_FOUND.getMessage(), USER_NOT_FOUND.getCode(), taskAnswer.getUserId().toString()));
            studentTaskAnswerDto.setName(student.getName());
            studentTaskAnswerDto.setSurname(student.getSurname());
            return;
        }

        Teacher teacher = teacherRepository.findById(taskAnswer.getUserId()).orElseThrow(() ->
                new UserNotFoundException(USER_NOT_FOUND.getMessage(), USER_NOT_FOUND.getCode(), taskAnswer.getUserId().toString()));
        studentTaskAnswerDto.setName(teacher.getName());
        studentTaskAnswerDto.setSurname(teacher.getSurname());
    }

}
