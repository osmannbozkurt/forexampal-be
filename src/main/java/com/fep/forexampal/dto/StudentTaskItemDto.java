package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentTaskItemDto {

    private Date startDate;
    private String startMonthAsName;
    private String startDay;
    private List<StudentTaskDto> studentTasks;
}
