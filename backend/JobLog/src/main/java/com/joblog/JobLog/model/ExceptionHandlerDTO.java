package com.joblog.JobLog.model;

public record ExceptionHandlerDTO(
        int status,
        String message,
        long timestamp
        ) {
}
