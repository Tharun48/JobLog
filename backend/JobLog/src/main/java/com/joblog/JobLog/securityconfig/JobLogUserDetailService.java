package com.joblog.JobLog.securityconfig;

import com.joblog.JobLog.repository.UserDetailsInterface;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class JobLogUserDetailService implements UserDetailsService {

    UserDetailsInterface userDetailsInterface;

    JobLogUserDetailService(UserDetailsInterface userDetailsInterface){
        this.userDetailsInterface=userDetailsInterface;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.joblog.JobLog.model.UserDetails userDetails = userDetailsInterface.findByEmail(userName);
        if(userDetails==null) throw new UsernameNotFoundException("user not found for the username " + userName);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(userDetails.getEmail(), userDetails.getPassword(), authorities);
    }
}
