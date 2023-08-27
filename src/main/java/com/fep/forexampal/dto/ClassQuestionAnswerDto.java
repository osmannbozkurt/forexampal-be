package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQuestionAnswerDto {

    private Long id;
    private String description;
    private boolean correct;
    private String imagePath;
    private int width;
    private int height;
    private Date answerDate;
    private String answerOwnerName;
    private String answerOwnerSurname;
    private Long answerOwnerId;
    private String answerOwnerType;
    private List<ClassQuestionAnswerDto> childAnswers;

}
