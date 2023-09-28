package com.fep.forexampal.mapper;

import com.fep.forexampal.common.utils.DateUtils;
import com.fep.forexampal.dto.StudentTaskAnswerDto;
import com.fep.forexampal.dto.StudentTaskAnswerRequestDto;
import com.fep.forexampal.dto.StudentTaskDetailResponseDto;
import com.fep.forexampal.dto.StudentTaskDto;
import com.fep.forexampal.dto.TaskMaterialDto;
import com.fep.forexampal.persistence.entity.StudentTask;
import com.fep.forexampal.persistence.entity.StudentTaskAnswer;
import com.fep.forexampal.persistence.entity.TaskMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Date;
import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface StudentTaskMapper {

    @Mapping(target = "description", source = "task.description")
    @Mapping(target = "taskType", source = "task.taskType")
    @Mapping(target = "startDay", source = "task.startDay")
    @Mapping(target = "startHour", source = "task.startHour")
    @Mapping(target = "subject", source = "task.subject.name")
    StudentTaskDto toTaskDto(StudentTask studentTask);

    List<StudentTaskDto> toStudentTaskDtoList(List<StudentTask> studentTasks);

    StudentTaskAnswer toStudentTaskAnswer(StudentTaskAnswerRequestDto requestDto);

    @Mapping(target = "description", source = "task.description")
    @Mapping(target = "taskType", source = "task.taskType")
    @Mapping(target = "teacherName", source = "teacher.name")
    @Mapping(target = "teacherSurname", source = "teacher.surname")
    @Mapping(target = "endMonthAsName", source = "task.endDay", qualifiedByName = "convertToMonthAsName")
    @Mapping(target = "endHour", source = "task.endHour")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "taskMaterials", source = "task.taskMaterials")
    StudentTaskDetailResponseDto toDetailResponseDto(StudentTask studentTask);

    @Mapping(target = "path", source = "path", qualifiedByName = "generateFileUrl")
    TaskMaterialDto toTaskMaterialDto(TaskMaterial taskMaterial);

    @Mapping(source = "imagePath", target = "imagePath", qualifiedByName = "generateFileUrl")
    StudentTaskAnswerDto toStudentTaskAnswerDto(StudentTaskAnswer studentTaskAnswer);


    @Named("generateFileUrl")
    default String generateFileUrl(String imagePath) {
        return  imagePath;
    }

    @Named("convertToMonthAsName")
    default String convertToMonthAsName(Date date) {
        return DateUtils.getMonthAsName(date);
    }

}
