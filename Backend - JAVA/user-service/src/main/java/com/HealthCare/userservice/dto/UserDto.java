package com.HealthCare.userservice.dto;

import com.HealthCare.userservice.entity.Role;
import com.HealthCare.userservice.entity.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class UserDto {

    private String firstname;
    private String lastname;
    private int age;
    private Date dob;
    private Sex sex;
    private String email;
    private String password;
    private int height;
    private int weight;
    private String image;
//    private Role role;
//    private boolean isEnabled;
//    private String otp;
//    private LocalDateTime otpGeneratedDateTime;

}
