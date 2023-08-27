package com.fep.forexampal.dto;

import com.fep.forexampal.common.enums.ClassQuestionFilterSolvedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassFilterOptions {

    private Long subjectId;
    private Long childSubjectId;
    private Date date;
    private boolean isRewarded;
    private ClassQuestionFilterSolvedStatus questionStatus;
}
