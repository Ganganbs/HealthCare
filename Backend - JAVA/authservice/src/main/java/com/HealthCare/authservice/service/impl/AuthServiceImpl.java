package com.HealthCare.authservice.service.impl;

import com.HealthCare.authservice.dto.SignupRequest;
import com.HealthCare.authservice.entity.Role;
import com.HealthCare.authservice.entity.UserCredential;
import com.HealthCare.authservice.repository.UserRepository;
import com.HealthCare.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserCredential createUser(SignupRequest signupRequest) {

        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return null;
        }

        UserCredential users = new UserCredential();
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        users.setId(signupRequest.getId());
        users.setEmail(signupRequest.getEmail());
        users.setName(signupRequest.getName());
        users.setPassword(hashedPassword);
        users.setRole(signupRequest.getRole());
        if(users.getRole().equals(Role.USER)){
            users.setBlocked(true);
        }else {
            users.setBlocked(false);
        }
        return userRepository.save(users);
    }

    @Override
    public void blockUser(UUID userId) {
        Optional<UserCredential> User = userRepository.findById(userId);
        if (User.isPresent()) {
            UserCredential user = User.get();
            user.setBlocked(!user.isBlocked());
            userRepository.save(user);
        }
    }


}
