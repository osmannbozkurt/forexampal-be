package com.fep.forexampal.exception;

import java.io.Serial;

public class ClassQuestionAnswerNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = -4005101761486502090L;

    public ClassQuestionAnswerNotFoundException(String message, int code, String... args) {
        super(message, code, args);
    }
}
