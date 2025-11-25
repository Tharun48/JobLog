package com.joblog.JobLog.repository;

import com.joblog.JobLog.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsInterface extends JpaRepository<UserDetails, String> {
    UserDetails findByEmail(String name);
}
