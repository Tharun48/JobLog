package com.joblog.JobLog.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Embeddable
public class JobApplicationId implements Serializable {
    private String companyName;
    private String jobId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobApplicationId)) return false;
        JobApplicationId that = (JobApplicationId) o;
        return Objects.equals(companyName, that.companyName) &&
                Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, jobId);
    }

}
