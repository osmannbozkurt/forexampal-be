package com.fep.forexampal.exception;

import java.io.Serial;

public class SystemException extends BaseException {

    @Serial
    private static final long serialVersionUID = -6076893671570532913L;

    public SystemException(String message, int code, String... args) {
        super(message, code, args);
    }
}
