package com.joblog.JobLog.exceptionhandler;

import com.joblog.JobLog.model.ExceptionHandlerDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ExceptionHandlerDTO> userAlreadyException(UserAlreadyExistsException r){
        ExceptionHandlerDTO e = new ExceptionHandlerDTO(HttpStatus.BAD_REQUEST.value(),r.getMessage(),
                System.currentTimeMillis());
        return  new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ExceptionHandlerDTO> userNotFoundException(UserNotFoundException r){
        ExceptionHandlerDTO e = new ExceptionHandlerDTO(HttpStatus.NOT_FOUND.value(),r.getMessage(),
                System.currentTimeMillis());
        return  new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ExceptionHandlerDTO> passwordException(PasswordMisMatchException r){
        ExceptionHandlerDTO e = new ExceptionHandlerDTO(HttpStatus.BAD_REQUEST.value(),r.getMessage(),
                System.currentTimeMillis());
        return  new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
    }

}
