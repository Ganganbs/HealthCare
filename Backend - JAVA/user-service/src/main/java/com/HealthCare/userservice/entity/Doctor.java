package com.HealthCare.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor extends BaseEntity{

    @NotNull(message = "name is empty")
    private String name;

    private String lastname;

    @NotNull(message = "sex is empty")
    private Sex sex;

    private int age;

    @NotNull(message = "dob is empty")
    private Date dob;

    @Column(unique = true)
    @NotNull(message = "email is empty")
    private String email;

    @NotNull(message = "password is empty")
    private String password;

    @NotNull(message = "experience is empty")
    private int experience;

    @NotNull(message = "specilised is empty")
    private String specilised;

    @NotNull(message = "Hospital is empty")
    private String hospital;

    @NotNull(message = "licenceNo is empty")
    private String licenceno;

//    @NotNull(message = "licence Image is empty")
//    private String licenceImg;

}
