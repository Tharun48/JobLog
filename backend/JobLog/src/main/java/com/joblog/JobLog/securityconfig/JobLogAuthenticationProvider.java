package com.joblog.JobLog.securityconfig;


import ch.qos.logback.classic.spi.IThrowableProxy;
import com.joblog.JobLog.exceptionhandler.UserNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JobLogAuthenticationProvider implements AuthenticationProvider {

    JobLogUserDetailService jobLogUserDetailService;
    PasswordEncoder passwordEncoder;

    JobLogAuthenticationProvider(JobLogUserDetailService jobLogUserDetailService,PasswordEncoder passwordEncoder) {
        this.jobLogUserDetailService=jobLogUserDetailService;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = jobLogUserDetailService.loadUserByUsername(userName);
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("UserName and password is not matching ");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(userName,password,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
