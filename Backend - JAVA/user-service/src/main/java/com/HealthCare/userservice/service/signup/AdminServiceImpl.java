package com.HealthCare.userservice.service.signup;

import com.HealthCare.userservice.entity.Role;
import com.HealthCare.userservice.entity.User;
import com.HealthCare.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllPatients() {
        return userRepository.findAll().stream().filter(user->user.getRole().equals(Role.USER)).toList();
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }
}
