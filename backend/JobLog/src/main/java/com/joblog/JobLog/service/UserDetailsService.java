package com.joblog.JobLog.service;

import com.joblog.JobLog.exceptionhandler.PasswordMisMatchException;
import com.joblog.JobLog.exceptionhandler.UserAlreadyExistsException;
import com.joblog.JobLog.exceptionhandler.UserNotFoundException;
import com.joblog.JobLog.mapper.UserMapper;
import com.joblog.JobLog.model.UserDetails;
import com.joblog.JobLog.model.UserDetailsDTO;
import com.joblog.JobLog.repository.UserDetailsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
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

    public UserDetails createUser(UserDetailsDTO userDetailsDTO){

        //password validation
        if(!validatePassword(userDetailsDTO.password(),userDetailsDTO.confirmPassword())) {
            throw new PasswordMisMatchException("password mismatch or " + userDetailsDTO.password() + " is not strong");
        }
        String hashedPassword = passwordEncoder.encode(userDetailsDTO.password());

        UserDetails userDetails = userMapper.toEntity(userDetailsDTO);
        userDetails.setPassword(hashedPassword);

        //have to find if there exists user with the same username
        UserDetails existingUser = userDetailsInterface.findByEmail(userDetails.getEmail());
        if(existingUser!=null) {
            throw new UserAlreadyExistsException("user already exists with user name " + userDetails.getEmail());
        }
        return userDetailsInterface.save(userDetails);
    }

    public UserDetails modifyUser(UserDetails userDetails){
        //have to find if there exists user with the same username
        UserDetails existingUser = userDetailsInterface.findByEmail(userDetails.getEmail());
        if(existingUser==null) {
            throw new UserNotFoundException("user does not exist with the username " + userDetails.getEmail() + " to modify the details");
        }
        return userDetailsInterface.save(userDetails);
    }

    public UserDetails getUserDetails(String userName){
        UserDetails userDetails = userDetailsInterface.findByEmail(userName);
        if(userDetails==null) throw new UserNotFoundException("user does not exist with the username " + userName);
        return userDetails;
    }

    public String deleteUser(String userName){
        UserDetails userDetails = getUserDetails(userName);
        String name = userDetails.getName();
        userDetailsInterface.delete(userDetails);
        return name;
    }


}
