package com.HealthCare.authservice.dto;

import com.HealthCare.authservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SignupRequest {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
