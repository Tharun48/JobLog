package com.joblog.JobLog.mapper;

import com.joblog.JobLog.model.UserDetails;
import com.joblog.JobLog.model.UserDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDetailsDTO toDto(UserDetails userDetails);
    UserDetails toEntity(UserDetailsDTO userDetailsDTO);
}
