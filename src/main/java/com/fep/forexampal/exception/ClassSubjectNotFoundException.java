package com.fep.forexampal.exception;

import java.io.Serial;

public class ClassSubjectNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = -4094010991766925895L;

    public ClassSubjectNotFoundException(String message, int code, String... args) {
        super(message, code, args);
    }
}
