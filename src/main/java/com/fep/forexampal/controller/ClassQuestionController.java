package com.fep.forexampal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fep.forexampal.common.enums.ClassQuestionFilterSolvedStatus;
import com.fep.forexampal.dto.ClassFilterOptions;
import com.fep.forexampal.dto.ClassQuestionAddRequestDto;
import com.fep.forexampal.dto.ClassQuestionAnswerAddRequestDto;
import com.fep.forexampal.dto.ClassQuestionDetailResponseDto;
import com.fep.forexampal.dto.ClassQuestionResponseDto;
import com.fep.forexampal.service.ClassQuestionService;
import com.fep.forexampal.validator.ValidMedia;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("class-questions")
@RequiredArgsConstructor
public class ClassQuestionController {

    private final ClassQuestionService classQuestionService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<ClassQuestionResponseDto> findAll(@PageableDefault Pageable pageable,
                                                            @RequestParam(required = false) Long subjectId,
                                                            @RequestParam(required = false) Long childSubjectId,
                                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date date,
                                                            @RequestParam(required = false) Boolean isRewarded,
                                                            @RequestParam(required = false) ClassQuestionFilterSolvedStatus questionStatus) {
        ClassFilterOptions.ClassFilterOptionsBuilder filterOptions = ClassFilterOptions.builder();
        Optional.ofNullable(subjectId).ifPresent(filterOptions::subjectId);
        Optional.ofNullable(childSubjectId).ifPresent(filterOptions::childSubjectId);
        Optional.ofNullable(date).ifPresent(filterOptions::date);
        Optional.ofNullable(isRewarded).ifPresent(filterOptions::isRewarded);
        Optional.ofNullable(questionStatus).ifPresent(filterOptions::questionStatus);
        return ResponseEntity.ok(classQuestionService.findAll(filterOptions.build(), pageable));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addQuestion(@RequestParam Long studentId,
                                            @RequestPart String classQuestion,
                                            @ValidMedia @RequestPart(required = false) MultipartFile file) throws JsonProcessingException {
        ClassQuestionAddRequestDto classQuestionAddRequestDto = objectMapper.readValue(classQuestion, ClassQuestionAddRequestDto.class);
        classQuestionService.saveQuestion(studentId, classQuestionAddRequestDto, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/answer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addAnswer(@PathVariable Long id,
                                          @RequestPart String classQuestionAnswer,
                                          @ValidMedia @RequestPart(required = false) MultipartFile file) throws JsonProcessingException {
        ClassQuestionAnswerAddRequestDto classQuestionAnswerAddRequestDto = objectMapper.readValue(classQuestionAnswer, ClassQuestionAnswerAddRequestDto.class);
        classQuestionService.addAnswerToClasQuestion(id, classQuestionAnswerAddRequestDto, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/answer")
    public ResponseEntity<ClassQuestionDetailResponseDto> getDetail(@PathVariable Long id, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(classQuestionService.getDetail(id, pageable));
    }

    @PatchMapping(value = "{id}/answer/{answerId}")
    public ResponseEntity<Void> markAsCorrect(@PathVariable Long id, @PathVariable Long answerId) {
        classQuestionService.markAnswerAskCorrect(id, answerId);
        return ResponseEntity.noContent().build();
    }
}
