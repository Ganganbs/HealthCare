package com.HealthCare.userservice.repository;

import com.HealthCare.userservice.entity.Pharma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PharmaRepository extends JpaRepository<Pharma, UUID> {
    boolean existsByEmail(String email);
}
