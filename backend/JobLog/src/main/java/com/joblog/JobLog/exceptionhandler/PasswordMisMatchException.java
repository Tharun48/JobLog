package com.joblog.JobLog.exceptionhandler;

public class PasswordMisMatchException extends RuntimeException{
    public PasswordMisMatchException(String message){
        super(message);
    }
}
