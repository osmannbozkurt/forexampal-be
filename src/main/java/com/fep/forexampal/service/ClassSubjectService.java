package com.fep.forexampal.service;

import com.fep.forexampal.exception.ClassSubjectNotFoundException;
import com.fep.forexampal.persistence.entity.ClassSubject;
import com.fep.forexampal.persistence.repository.ClassSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.fep.forexampal.common.enums.ErrorMessage.CLASS_SUBJECT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ClassSubjectService {

    private final ClassSubjectRepository classSubjectRepository;

    public ClassSubject findBySubjectId(Long subjectId) {
        return classSubjectRepository.findClassSubjectBySubjectId(subjectId).orElseThrow(() ->
                new ClassSubjectNotFoundException(CLASS_SUBJECT_NOT_FOUND.getMessage(), CLASS_SUBJECT_NOT_FOUND.getCode(), subjectId.toString()));
    }
}
