package com.joblog.JobLog.rest;

import com.joblog.JobLog.ApplicationConstants.Application;
import com.joblog.JobLog.mapper.UserMapper;
import com.joblog.JobLog.model.*;
import com.joblog.JobLog.service.UserDetailsService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
public class UserRestController {

    UserDetailsService userService;
    UserMapper userMapper;
    AuthenticationManager authenticationManager;

    @Autowired
    UserRestController(UserDetailsService userService, UserMapper userMapper,AuthenticationManager authenticationManager){
        this.userService=userService;
        this.userMapper=userMapper;
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<MessageDTO> createUser(@RequestBody UserDetailsDTO userDetailsDTO){
        UserDetails userDetails1 =  userService.createUser(userDetailsDTO);
        MessageDTO messageDTO = new MessageDTO(userDetailsDTO.name(),"user created successfully");
        return new ResponseEntity<>(messageDTO, HttpStatus.CREATED);
    }

    @PutMapping("/user/signup")
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

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<MessageDTO> deleteUser(@PathVariable String userName){
        String name = userService.deleteUser(userName);
        MessageDTO messageDTO = new MessageDTO(name,"user deleted successfully");
        return new ResponseEntity<>(messageDTO,HttpStatus.OK);
    }

    //have to implement security for this end point to get the token for the loggedin user
    @PostMapping("/user/login")
    public ResponseEntity<JWTToken> loginUser(@RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginDTO.userName(),loginDTO.password());
        Authentication authenticated =  authenticationManager.authenticate(authentication);
        if(authenticated.isAuthenticated()) {
            String key = Application.key;
            SecretKey secretKey =  Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
            String token = Jwts.builder().signWith(secretKey)
                    .setSubject("job-log")
                    .claim("username",loginDTO.userName())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+10000000))
                    .compact();
            JWTToken jwtToken = new JWTToken("user logged in sucessfully",token);
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }
        JWTToken jwtToken = new JWTToken("login failed","");
        return new ResponseEntity<>(jwtToken,HttpStatus.BAD_REQUEST);
    }


}
