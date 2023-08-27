package com.fep.forexampal.exception;

import java.io.Serial;

public class UnsupportedMediaTypeException extends BaseException {
    @Serial
    private static final long serialVersionUID = -3884540684206232384L;

    public UnsupportedMediaTypeException(String message, int code, String... args) {
        super(message, code, args);
    }
}
