package com.fep.forexampal.dto;

import com.fep.forexampal.common.model.PagingModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQuestionDetailResponseDto {

    private ClassQuestionItemDto classQuestion;

    private List<ClassQuestionAnswerDto> classQuestionAnswers;

    private PagingModel paging;
}
