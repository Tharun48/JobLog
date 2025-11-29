package com.joblog.JobLog.model;

public record JobApplicationDTO(
        String username,
        String jobId,
        String companyName,
        String status,
        String resumeUrl,
        String appliedDate
) {
}
