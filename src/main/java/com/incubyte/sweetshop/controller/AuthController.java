package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;


    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestBody AuthRequest request
    ) {

        return ResponseEntity.status(201).body(
                Map.of(
                        "username", request.username,
                        "role", "USER"
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody AuthRequest request
    ) {

        String role = "USER";

        String token = jwtUtil.generateToken(
                request.username,
                role
        );

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }
}
