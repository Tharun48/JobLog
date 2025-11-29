package com.joblog.JobLog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

@Entity
@Getter @Setter
@Table(name="job_applications")
public class JobApplications {

    @EmbeddedId
    JobApplicationId id;

    String status;

    String resumeUrl;

    String appliedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="email")
    UserDetails userdetails;

    void updateUserDetails(UserDetails userDetails){
        this.userdetails=userDetails;
    }

}
