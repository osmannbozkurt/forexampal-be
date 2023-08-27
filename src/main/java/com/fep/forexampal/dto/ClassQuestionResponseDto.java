package com.fep.forexampal.dto;

import com.fep.forexampal.common.model.PagingModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQuestionResponseDto {

    private List<ClassQuestionItemDto> classQuestions;

    private PagingModel paging;
}
