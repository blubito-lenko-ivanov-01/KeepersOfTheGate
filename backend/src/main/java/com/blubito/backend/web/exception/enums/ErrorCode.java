package com.blubito.backend.web.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "GENERAL_UNAUTHORIZED"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "GENERAL_FORBIDDEN"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "GENERAL_BAD_REQUEST"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "GENERAL_METHOD_NOT_ALLOWED"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "GENERAL_NOT_FOUND"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GENERAL_SERVER_ERROR");

    private final HttpStatus httpStatus;
    private final String errorCode;

    public static ErrorCode fromHttpStatus(final HttpStatus httpStatus) {
        var errorCodeStream = Arrays.stream(ErrorCode.values());
        return errorCodeStream
                .filter(e -> e.getHttpStatus() == httpStatus)
                .findFirst()
                .orElse(ErrorCode.SERVER_ERROR);
    }

}
