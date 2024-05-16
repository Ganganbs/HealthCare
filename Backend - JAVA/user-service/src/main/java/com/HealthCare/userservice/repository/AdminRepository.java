package com.HealthCare.userservice.repository;

import com.HealthCare.userservice.entity.Admin;
import com.HealthCare.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    boolean existsByEmail(String email);
    Optional<Admin> findByEmail(String email);
}
