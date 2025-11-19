package com.example.cosmos.controller;

import com.example.cosmos.dto.LoginRequestDto;
import com.example.cosmos.dto.RegistrationDto;
import com.example.cosmos.security.JwtUtil;
import com.example.cosmos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationDto request) {
        Map<String, String> response = new HashMap<>();
        response.put("message", authService.register(request));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) throws Exception {
        Map<String, String> response = new HashMap<>();
        response.put("message", authService.login(request));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // remove "Bearer "

        boolean isValid = jwtUtil.validateToken(token);

        if (isValid) {
            String username = jwtUtil.extractUsername(token);
            return ResponseEntity.ok("Token is valid for user: " + username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
        }
    }
}
