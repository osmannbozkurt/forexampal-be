package com.fep.forexampal.exception;

import java.io.Serial;

public class CommonMediaException extends BaseException {

    @Serial
    private static final long serialVersionUID = -3964103026378883747L;

    public CommonMediaException(String message, int code, String... args) {
        super(message, code, args);
    }

}
