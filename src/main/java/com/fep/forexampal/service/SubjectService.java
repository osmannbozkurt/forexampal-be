package com.fep.forexampal.service;

import com.fep.forexampal.dto.SubjectDto;
import com.fep.forexampal.exception.SubjectNotFoundException;
import com.fep.forexampal.mapper.SubjectMapper;
import com.fep.forexampal.persistence.entity.Subject;
import com.fep.forexampal.persistence.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fep.forexampal.common.enums.ErrorMessage.SUBJECT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public List<SubjectDto> findAll() {
        List<Subject> notifications = subjectRepository.findAllByParentIsNull();
        return subjectMapper.subjectDtoList(notifications);
    }
    public List<SubjectDto> findAllBySubjectId(Long subjectId) {
        List<Subject> notifications = subjectRepository.findAllByParentId(subjectId);
        return subjectMapper.subjectDtoList(notifications);
    }

    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException(SUBJECT_NOT_FOUND.getMessage(), SUBJECT_NOT_FOUND.getCode(), id.toString()));
    }
}
