package com.HealthCare.authservice.service.impl;

import com.HealthCare.authservice.entity.UserCredential;
import com.HealthCare.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential users =
                userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(
                        "User not found with email: "+email));
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(users.getRole().name()));
        return new User(users.getEmail(), users.getPassword(), authorities);
    }

    public List<UserCredential> getAllUsers() {
        return userRepository.findAll();
    }

    public UserCredential getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }


}
