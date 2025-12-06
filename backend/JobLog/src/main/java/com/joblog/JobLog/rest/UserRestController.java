package com.joblog.JobLog.rest;

import com.joblog.JobLog.mapper.UserMapper;
import com.joblog.JobLog.model.MessageDTO;
import com.joblog.JobLog.model.UserDetails;
import com.joblog.JobLog.model.UserDetailsDTO;
import com.joblog.JobLog.service.UserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
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

    @PostMapping("/user/signup")
    public ResponseEntity<MessageDTO> createUser(@RequestBody UserDetailsDTO userDetailsDTO){
        UserDetails userDetails1 =  userService.createUser(userDetailsDTO);
        MessageDTO messageDTO = new MessageDTO(userDetailsDTO.name(),"user created successfully");
        return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
    }

    @PutMapping("/user")
    public ResponseEntity<String> modifyUser(@RequestBody UserDetailsDTO userDetailsDTO){
        UserDetails userDetails = userMapper.toEntity(userDetailsDTO);
        UserDetails userDetails1 =  userService.modifyUser(userDetails);
        return new ResponseEntity<>("User details modified", HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable String userName, @RequestHeader(name="token", required = false) String token){
        UserDetails userDetails = userService.getUserDetails(userName);
        UserDetailsDTO userDetailsDTO = userMapper.toDto(userDetails);
        return new ResponseEntity<>(userDetailsDTO,HttpStatus.OK);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<MessageDTO> deleteUser(@PathVariable String userName){
        String name = userService.deleteUser(userName);
        MessageDTO messageDTO = new MessageDTO(name,"user deleted successfully");
        return new ResponseEntity<>(messageDTO,HttpStatus.OK);
    }

    //have to implement security for this end point to get the token for the loggedin user
    @GetMapping("/user/login")
    public ResponseEntity<String> loginUser(@PathVariable String userName){
        return new ResponseEntity<>("user logged in successfully",HttpStatus.OK);
    }


}
