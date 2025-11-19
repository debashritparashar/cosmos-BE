package com.example.cosmos.service;

import com.example.cosmos.dto.LoginRequestDto;
import com.example.cosmos.dto.RegistrationDto;
import com.example.cosmos.repository.UserRepository;
import com.example.cosmos.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.cosmos.entity.User;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;


    public String register(RegistrationDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setFlatNumber(request.getFlatNumber());
        user.setBuildingName(request.getBuildingName());
        user.setRole("RESIDENT");

        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequestDto request) throws Exception {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        return "Token: " + jwtUtil.generateToken(user.getEmail());
    }
}
