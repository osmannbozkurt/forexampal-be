package com.fep.forexampal.exception;

import lombok.Getter;

import java.io.Serial;

public class BaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1199723793567198856L;

    @Getter
    private final String [] args;

    @Getter
    private final int code;

    public BaseException(String message, int code, String... args) {
        super(message);
        this.args = args;
        this.code = code;
    }
}
