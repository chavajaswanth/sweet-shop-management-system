package com.incubyte.sweetshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "username", request.username,
                        "role", "USER"
                ));
    }

}
