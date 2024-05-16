package com.HealthCare.userservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PharmaDto {

    private String storename;
    private String ownername;
    private String email;
    private String password;
}
