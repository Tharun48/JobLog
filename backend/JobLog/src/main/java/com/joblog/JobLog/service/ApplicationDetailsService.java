package com.joblog.JobLog.service;

import com.joblog.JobLog.mapper.ApplicationMapper;
import com.joblog.JobLog.model.JobApplicationResponseDTO;
import com.joblog.JobLog.model.JobApplications;
import com.joblog.JobLog.model.UserDetails;
import com.joblog.JobLog.repository.ApplicationDetailsInterface;
import com.joblog.JobLog.repository.UserDetailsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationDetailsService {


    ApplicationDetailsInterface applicationDetailsInterface;
    UserDetailsService userDetailsService;

    @Autowired
    ApplicationDetailsService(ApplicationDetailsInterface applicationDetailsInterface, UserDetailsService userDetailsService){
        this.applicationDetailsInterface=applicationDetailsInterface;
        this.userDetailsService=userDetailsService;
    }

    public JobApplicationResponseDTO createApplication(String username,JobApplications jobApplications){
        UserDetails userDetails = userDetailsService.getUserDetails(username);
        userDetails.updateApplication(jobApplications);
        applicationDetailsInterface.save(jobApplications);
        return new JobApplicationResponseDTO(userDetails.getName(),jobApplications.getId().getJobId(),jobApplications.getId().getCompanyName());
    }

    public JobApplicationResponseDTO modifyApplication(String username,JobApplications jobApplications){
        UserDetails userDetails = userDetailsService.getUserDetails(username);
        userDetails.updateApplication(jobApplications);
        applicationDetailsInterface.save(jobApplications);
        return new JobApplicationResponseDTO(userDetails.getName(),jobApplications.getId().getJobId(),jobApplications.getId().getCompanyName());
    }

    public List<JobApplications> getUserApplications(String userName,String jobId,String companyName){
        return applicationDetailsInterface.getApplications(userName,jobId,companyName);
    }

}
