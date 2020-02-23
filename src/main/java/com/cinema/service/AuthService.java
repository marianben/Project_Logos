package com.cinema.service;

import com.cinema.dto.request.SigninRequest;
import com.cinema.dto.request.SignupRequest;

public interface AuthService {


    void registerUser(SignupRequest request);


    String signin(SigninRequest request);

    Boolean checkTokenExpired(String token);
}