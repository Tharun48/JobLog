package com.joblog.JobLog.repository;

import com.joblog.JobLog.model.JobApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationDetailsInterface extends JpaRepository<JobApplications, Integer> {

}
