package com.joblog.JobLog.service;

import com.joblog.JobLog.model.UserDetails;
import com.joblog.JobLog.repository.UserDetailsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    UserDetailsInterface userDetailsInterface;
    @Autowired
    UserDetailsService(UserDetailsInterface userDetailsInterface){
        this.userDetailsInterface=userDetailsInterface;
    }

    public UserDetails createUser(UserDetails userDetails){
        UserDetails userDetails1 = userDetailsInterface.save(userDetails);
        return userDetails1;
    }


}
