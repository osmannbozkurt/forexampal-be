package com.fep.forexampal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTaskAnswerRequestDto {

    @NotNull(message = "validation.studentTaskId.not.null")
    private Long studentTaskId;

    @NotNull(message = "validation.userId.not.null")
    private Long userId;

    @NotNull(message = "validation.userType.not.null")
    private String userType;

    private String commentAnswer;
}
