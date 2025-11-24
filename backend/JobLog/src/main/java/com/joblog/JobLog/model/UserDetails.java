package com.joblog.JobLog.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "user_details")
@Getter @Setter
public class UserDetails {

    String name;

    @Id
    String email;

    long phoneNumber;

    String password;

    Date dateOfBirth;

}
