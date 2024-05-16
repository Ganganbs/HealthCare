package com.HealthCare.userservice.dto;

import com.HealthCare.userservice.entity.Role;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class LoginDetails {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
