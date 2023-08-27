package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQuestionAddRequestDto {

    private String description;
    private List<Long> taggedUsers;
    private int reward;
    private Long subjectId;
    private String correctAnswer;
}
