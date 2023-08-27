package com.fep.forexampal.exception;

import java.io.Serial;

public class SubjectNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = -3705996962152190495L;

    public SubjectNotFoundException(String message, int code, String... args) {
        super(message, code, args);
    }
}
