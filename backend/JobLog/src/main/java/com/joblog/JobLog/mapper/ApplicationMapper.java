package com.joblog.JobLog.mapper;

import com.joblog.JobLog.JobLogApplication;
import com.joblog.JobLog.model.JobApplicationDTO;
import com.joblog.JobLog.model.JobApplications;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    @Mapping(source = "jobId",target="id.jobId")
    @Mapping(source = "companyName",target="id.companyName")
    public JobApplications toEntity(JobApplicationDTO jobApplicationDTO);



    @Mapping(source = "id.jobId",target="jobId")
    @Mapping(source = "id.companyName",target="companyName")
    @Mapping(source = "userdetails.email",target="username")
    public JobApplicationDTO toDto(JobApplications applications);


    public List<JobApplicationDTO> toDto(List<JobApplications> applications);

}
