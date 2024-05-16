package com.HealthCare.authservice.repository;

import com.HealthCare.authservice.entity.Role;
import com.HealthCare.authservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserCredential,UUID> {

    Optional<UserCredential> findByEmail(String email);
//    boolean existByRole(Role role);
    boolean existsByEmail(String email);
}
