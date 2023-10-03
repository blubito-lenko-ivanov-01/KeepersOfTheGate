package com.blubito.backend.web.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {
    private final String uuid;
    private final String requestTrackingId;
    private final String errorCode;
    private final String debugMessage;
    private final String stackTrace;
}
