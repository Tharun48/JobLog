package com.joblog.JobLog.rest;

import com.joblog.JobLog.mapper.UserMapper;
import com.joblog.JobLog.model.UserDetails;
import com.joblog.JobLog.model.UserDetailsDTO;
import com.joblog.JobLog.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    UserDetailsService userService;
    UserMapper userMapper;

    @Autowired
    UserRestController(UserDetailsService userService, UserMapper userMapper){
        this.userService=userService;
        this.userMapper=userMapper;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody UserDetailsDTO userDetailsDTO){
        UserDetails userDetails = userMapper.toEntity(userDetailsDTO);
        UserDetails userDetails1 =  userService.createUser(userDetails);
        UserDetailsDTO userDetailsDTO1 = userMapper.toDto(userDetails1);
        return new ResponseEntity<>(userDetailsDTO1, HttpStatus.CREATED);
    }

}
