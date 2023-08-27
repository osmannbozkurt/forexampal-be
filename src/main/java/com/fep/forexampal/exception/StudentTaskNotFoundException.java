package com.fep.forexampal.exception;

import java.io.Serial;

public class StudentTaskNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = 4959385968747161393L;

    public StudentTaskNotFoundException(String message, int code, String... args) {
        super(message, code, args);
    }
}
