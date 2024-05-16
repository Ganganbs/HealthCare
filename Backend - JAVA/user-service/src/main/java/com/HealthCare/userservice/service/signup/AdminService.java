package com.HealthCare.userservice.service.signup;

import com.HealthCare.userservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface AdminService {

    List<User> getAllPatients();

    Optional<User> getUserById(UUID userId);
}
