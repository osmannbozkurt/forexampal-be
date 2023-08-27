package com.fep.forexampal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fep.forexampal.dto.StudentTaskAnswerRequestDto;
import com.fep.forexampal.dto.StudentTaskDetailResponseDto;
import com.fep.forexampal.dto.StudentTaskResponseDto;
import com.fep.forexampal.service.StudentTaskService;
import com.fep.forexampal.validator.ValidMedia;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Validated
public class StudentTaskController {

    private final StudentTaskService studentTaskService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentTaskResponseDto> getAllByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentTaskService.getAllByStudentId(studentId));
    }

    @PostMapping
    public ResponseEntity<Void> saveTaskAnswer(@RequestPart("taskAnswer") String taskAnswerStr,
                                               @ValidMedia @RequestPart(required = false) MultipartFile file) throws IOException {
        StudentTaskAnswerRequestDto taskAnswer = objectMapper.readValue(taskAnswerStr, StudentTaskAnswerRequestDto.class);
        studentTaskService.saveStudentTaskAnswer(taskAnswer, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<StudentTaskDetailResponseDto> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(studentTaskService.getDetailById(id));
    }
}
