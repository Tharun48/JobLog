package com.joblog.JobLog.model;

public record JobApplicationResponseDTO(
        String name,
        String jobId,
        String companyName
) {
}
