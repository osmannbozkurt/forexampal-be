package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQuestionAnswerAddRequestDto {

    private String description;
    private List<Long> taggedUsers;
    private Long answerOwnerUser;
    private Long parentAnswerId;
}
