package com.HealthCare.userservice.dto;

import com.HealthCare.userservice.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDto {
    private String name;
    private String email;
    private String password;
    private String image;
    private Role role;
}
