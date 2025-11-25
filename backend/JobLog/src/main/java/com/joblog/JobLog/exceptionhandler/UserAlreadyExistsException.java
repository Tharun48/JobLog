package com.joblog.JobLog.exceptionhandler;

import org.apache.catalina.User;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
