package com.fep.forexampal.exception;

import java.io.Serial;

public class UserNotFoundException extends BaseException {

    @Serial
    private static final long serialVersionUID = 4959385968747161393L;

    public UserNotFoundException(String message, int code, String... args) {
        super(message, code, args);
    }
}
