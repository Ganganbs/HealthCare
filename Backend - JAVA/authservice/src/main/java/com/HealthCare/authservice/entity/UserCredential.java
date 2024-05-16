package com.HealthCare.authservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class UserCredential {

        @Id
        private UUID id;
        private String name;
        private String email;
        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        @CreationTimestamp
        @Column(updatable = false)
        private Timestamp createdDate;

        @UpdateTimestamp
        private Timestamp lastModifiedDate;

        private boolean blocked;
    }
