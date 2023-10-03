package com.blubito.backend.web.exception;

import com.blubito.backend.web.exception.enums.ErrorCode;

public class HttpNotFoundException extends DebuggableException {
    public HttpNotFoundException(String debugMessage) {
        super(ErrorCode.NOT_FOUND, debugMessage);
    }

    public HttpNotFoundException(String debugMessage, Throwable throwable) {
        super(ErrorCode.NOT_FOUND, debugMessage, throwable);
    }
}
