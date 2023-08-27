package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTaskDto {
    private Long id;
    private String description;
    private String taskType;
    private Date startDay;
    private String startHour;
    private String status;
    private String subject;
    private BigDecimal grade;
    private String startDayAsName;
    private String startMonthAsName;
}
