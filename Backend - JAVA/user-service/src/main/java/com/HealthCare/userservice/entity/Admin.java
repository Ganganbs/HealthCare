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
public class Admin extends BaseEntity{
    @NotNull(message = "name is empty")
    private String name;

    @Column(unique = true)
    @NotNull(message = "email is empty")
    private String email;

    @NotNull(message = "password is empty")
    private String password;

    private String image;
}
