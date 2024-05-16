package com.HealthCare.authservice.service;

import com.HealthCare.authservice.dto.SignupRequest;
import com.HealthCare.authservice.entity.UserCredential;

import java.util.UUID;

public interface AuthService {
    UserCredential createUser(SignupRequest signupRequest);

    void blockUser(UUID userId);
}
