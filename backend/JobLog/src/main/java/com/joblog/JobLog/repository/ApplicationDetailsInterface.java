package com.joblog.JobLog.repository;

import com.joblog.JobLog.model.JobApplicationId;
import com.joblog.JobLog.model.JobApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationDetailsInterface extends JpaRepository<JobApplications, JobApplicationId> {
    @Query("select j from JobApplications j where (:job_id is null or  j.id.jobId= :job_id) " +
            "and (:company_name is null or j.id.companyName= :company_name)" +
            "and (:email is null or j.userdetails.email = :email)" )
    List<JobApplications> getApplications(@Param("email") String email, @Param("job_id") String jobId, @Param("company_name") String companyName);
}
