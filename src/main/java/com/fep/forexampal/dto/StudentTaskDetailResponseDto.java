package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentTaskDetailResponseDto {

    private Long id;
    private String description;
    private String taskType;
    private String teacherName;
    private String teacherSurname;
    private String endMonthAsName;
    private String endHour;
    private String status;
    private List<TaskMaterialDto> taskMaterials;
    private List<StudentTaskAnswerDto> answers;

}
