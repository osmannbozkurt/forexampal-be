package com.fep.forexampal.controller;

import com.fep.forexampal.dto.SubjectDto;
import com.fep.forexampal.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectDto> getAll(){
        return subjectService.findAll();
    }
    @GetMapping("/{id}")
    public List<SubjectDto> getAllById(@PathVariable Long id){
        return subjectService.findAllBySubjectId(id);
    }
}
