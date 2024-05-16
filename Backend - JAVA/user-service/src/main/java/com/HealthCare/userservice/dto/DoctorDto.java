package com.HealthCare.userservice.dto;

import com.HealthCare.userservice.entity.Sex;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
public class DoctorDto {

    private String name;
    private String lastname;
    private Sex sex;
    private int age;
    private Date dob;
    private String email;
    private String password;
    private int experience;
    private String specilised;
    private String hospital;
    private String licenceno;
    private MultipartFile licenceImg;

}
