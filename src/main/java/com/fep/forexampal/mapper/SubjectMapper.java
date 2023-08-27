package com.fep.forexampal.mapper;


import com.fep.forexampal.dto.SubjectDto;
import com.fep.forexampal.persistence.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SubjectMapper {

    SubjectDto subjectDto(Subject subject);

    List<SubjectDto> subjectDtoList(List<Subject> subjects);
}
