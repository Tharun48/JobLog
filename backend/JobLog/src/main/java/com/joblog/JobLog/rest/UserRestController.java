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
    public ResponseEntity<String> createUser(@RequestBody UserDetailsDTO userDetailsDTO){
        UserDetails userDetails1 =  userService.createUser(userDetailsDTO);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<String> modifyUser(@RequestBody UserDetailsDTO userDetailsDTO){
        UserDetails userDetails = userMapper.toEntity(userDetailsDTO);
        UserDetails userDetails1 =  userService.modifyUser(userDetails);
        return new ResponseEntity<>("User details modified", HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable String userName){
        UserDetails userDetails = userService.getUserDetails(userName);
        UserDetailsDTO userDetailsDTO = userMapper.toDto(userDetails);
        return new ResponseEntity<>(userDetailsDTO,HttpStatus.OK);
    }

}
