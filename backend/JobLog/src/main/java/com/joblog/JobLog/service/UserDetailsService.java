package com.joblog.JobLog.service;

import com.joblog.JobLog.exceptionhandler.PasswordMisMatchException;
import com.joblog.JobLog.exceptionhandler.UserAlreadyExistsException;
import com.joblog.JobLog.exceptionhandler.UserNotFoundException;
import com.joblog.JobLog.mapper.UserMapper;
import com.joblog.JobLog.model.UserDetails;
import com.joblog.JobLog.repository.UserDetailsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDetailsService {

    UserDetailsInterface userDetailsInterface;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailsService(UserDetailsInterface userDetailsInterface, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userDetailsInterface=userDetailsInterface;
        this.userMapper=userMapper;
        this.passwordEncoder=passwordEncoder;
    }



    private boolean passwordMatches(String password,String confirmPassword) {
        return password.matches(confirmPassword);
    }

    private boolean isPasswordStrong(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&\\-+=()])(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if(password==null) return false;
        Matcher m = p.matcher(password);
        return m.matches();
    }

    private boolean validatePassword(String password,String confirmPassword){
        return passwordMatches(password,confirmPassword) && isPasswordStrong(password);
    }

}
