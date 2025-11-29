package com.joblog.JobLog.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_details")
@Getter @Setter
public class UserDetails {

    String name;

    @Id
    String email;

    long phoneNumber;

    String password;

    Date dateOfBirth;

    @OneToMany(mappedBy = "userdetails",fetch = FetchType.LAZY)
    List<JobApplications> applications;


    public void updateApplication(JobApplications jobApplication){
        if(applications.isEmpty()) applications = new ArrayList<>();
        applications.add(jobApplication);
        jobApplication.updateUserDetails(this);
    }


}
