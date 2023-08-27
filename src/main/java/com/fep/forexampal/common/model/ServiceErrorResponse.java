package com.fep.forexampal.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceErrorResponse {

    private int code;
    private String message;
}
