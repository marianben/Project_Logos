package com.cinema.controller;

import com.cinema.dto.request.SigninRequest;
import com.cinema.dto.request.SigninResponse;
import com.cinema.dto.request.SignupRequest;
import com.cinema.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignupRequest request) {
        authService.registerUser(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest signinRequest) {
        String token = authService.signin(signinRequest);
        return ResponseEntity.ok(new SigninResponse(token));
    }

    @GetMapping("check")
    public ResponseEntity<?> checkTokenExpired(@RequestParam String token) {
        return ResponseEntity.ok(authService.checkTokenExpired(token));
    }
}