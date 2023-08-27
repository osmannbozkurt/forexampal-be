package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTaskAnswerDto {

    private String name;
    private String surname;
    private Date answerDate;
    private String imagePath;
    private String userType;
    private String commentAnswer;
    private int width;
    private int height;
}
