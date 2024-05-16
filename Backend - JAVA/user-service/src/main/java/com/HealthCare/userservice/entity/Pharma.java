package com.HealthCare.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pharma extends BaseEntity{

    @NotNull(message = "storename is empty")
    private String storename;

    @NotNull(message = "ownername is empty")
    private String ownername;

    @Column(unique = true)
    @NotNull(message = "email is empty")
    private String email;

    @NotNull(message = "password is empty")
    private String password;

}
