package com.blubito.backend.web.exception;

import com.blubito.backend.web.exception.enums.ErrorCode;

public class HttpBadRequestException extends DebuggableException{
    public static String ID_NON_NULL = "Provided entity already has an id";
    public static String ID_NULL = "Provided entity doesn't have an id";

    public HttpBadRequestException(String debugMessage) {
        super(ErrorCode.BAD_REQUEST, debugMessage);
    }

    public HttpBadRequestException(String debugMessage, Throwable throwable) {
        super(ErrorCode.BAD_REQUEST, debugMessage, throwable);
    }

}
