package com.joblog.JobLog.securityconfig;

import com.joblog.JobLog.filter.JwtTokenValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.net.http.HttpRequest;
@EnableWebSecurity
@Configuration
public class JobLogSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((http)->http
                    .requestMatchers(HttpMethod.POST,"/user/signup").permitAll()
                    .requestMatchers(HttpMethod.POST,"/user/login").permitAll()
                    .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                    .anyRequest().authenticated()

        );
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(JobLogUserDetailService jobLogUserDetailService,PasswordEncoder passwordEncoder) {
        JobLogAuthenticationProvider jobLogAuthenticationProvider = new JobLogAuthenticationProvider(jobLogUserDetailService,passwordEncoder);
        ProviderManager providerManager = new ProviderManager(jobLogAuthenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }




}
