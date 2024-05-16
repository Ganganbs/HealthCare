package com.HealthCare.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity{
    @NotNull(message = "name is empty")
    private String firstname;

    private String lastname;

    @NotNull(message = "age is empty")
    private int age;

    @NotNull(message = "DOB is empty")
    private Date dob;

    @NotNull(message = "sex is empty")
    private Sex sex;

    @Column(unique = true)
    @NotNull(message = "email is empty")
    private String email;

    @NotNull(message = "password is empty")
    private String password;

    private int height;
    private int weight;

    private String image;

}
