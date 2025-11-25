package com.joblog.JobLog.model;

import java.util.Date;

public record UserDetailsDTO(
        String name,
        String email,
        long phoneNumber,
        String password,
        String confirmPassword,
        Date dateOfBirth
) {
}
