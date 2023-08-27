package com.fep.forexampal.exception;

import java.io.Serial;

public class ClassQuestionNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = -1114744640382213600L;

    public ClassQuestionNotFoundException(String message, int code, String... args) {
        super(message, code, args);
    }
}
