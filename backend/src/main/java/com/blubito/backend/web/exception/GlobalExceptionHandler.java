package com.blubito.backend.web.exception;

import com.blubito.backend.web.exception.enums.ErrorCode;
import com.blubito.backend.web.exception.utils.GlobalExceptionHandlerUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${global.exception-handling.debug-message:false}")
    private boolean isDebugMessageEnabled;

    //This exception is thrown when we send a request with an unsupported HTTP method
    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
                                                                         final @NonNull HttpHeaders headers,
                                                                         final @NonNull HttpStatus status,
                                                                         final @NonNull WebRequest request) {
        final StringBuilder sb = new StringBuilder()
                .append(ex.getMethod())
                .append(" method is not supported for this request. Supported methods are ");

        Objects.requireNonNull(ex.getSupportedHttpMethods()).forEach(method -> sb.append(method).append(" "));

        final String errorMsg = sb.toString();
        return buildErrorDTO(ErrorCode.METHOD_NOT_ALLOWED, errorMsg, ex);
    }

    //This exception is thrown when the request is missing a parameter
    @Override
    @NonNull
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
                                                                          final @NonNull HttpHeaders headers,
                                                                          final @NonNull HttpStatus status,
                                                                          final @NonNull WebRequest request) {
        final var errorMsg = "Missing request parameter: " + ex.getParameterName();
        return buildErrorDTO(ErrorCode.BAD_REQUEST, errorMsg, ex);
    }

    //This exception is thrown when an argument annotated with @Valid failed validation
    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        final var errors = new StringBuilder();

        errors.append("Field errors: ");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": " + error.getDefaultMessage());
        }

        errors.append("Object errors: ");
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.append(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        final var errorMsg = errors.toString();
        return buildErrorDTO(ErrorCode.fromHttpStatus(status), errorMsg, ex);
    }

    //This exception is thrown when the client sends a request with unsupported media type
    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
                                                                     final HttpHeaders headers,
                                                                     final HttpStatus status,
                                                                     final WebRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getContentType())
                .append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(mediaType -> sb.append(mediaType).append(" "));

        final var errorMsg = sb.toString();
        return buildErrorDTO(ErrorCode.fromHttpStatus(status), errorMsg, ex);
    }

    //A catch-all type of logic that deals with all other exceptions that don't have specific handlers
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleUnhandledException(final Throwable throwable, final WebRequest request) {
        if (throwable instanceof IllegalStateException) {
            return buildErrorDTO(new DebuggableException(ErrorCode.BAD_REQUEST, throwable.getMessage(), throwable));
        }

        if (throwable instanceof AccessDeniedException) {
            return buildErrorDTO(new DebuggableException(ErrorCode.FORBIDDEN, throwable.getMessage(), throwable));
        }

        return buildErrorDTO(new DebuggableException(ErrorCode.SERVER_ERROR, throwable.getMessage(), throwable));
    }

    @ExceptionHandler(DebuggableException.class)
    protected ResponseEntity<Object> handleConflict(final DebuggableException exception, final WebRequest request) {
        return buildErrorDTO(exception);
    }

    private ResponseEntity<Object> buildErrorDTO(final DebuggableException ex) {
        return buildErrorDTO(ex.getErrorCode(), ex.getDebugMessage(), ex);
    }

    private ResponseEntity<Object> buildErrorDTO(final ErrorCode errorCode, final String errorMsg, final Throwable throwable) {
        final var errorUUID = UUID.randomUUID().toString();
        log.error("Error: UUID [{}] - {}", errorUUID, errorMsg);

        final var errorDTO = GlobalExceptionHandlerUtil
                .buildApiErrorDTO(errorUUID, errorCode, errorMsg, throwable, isDebugMessageEnabled);

        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorCode.getHttpStatus());
    }

}
