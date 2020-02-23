package com.cinema.service.impl;


import com.cinema.Exeption.AlreadyExistsException;
import com.cinema.Exeption.NotFoundException;
import com.cinema.config.jwt.JwtTokenProvider;
import com.cinema.dto.request.SigninRequest;
import com.cinema.dto.request.SignupRequest;
import com.cinema.entity.RoleEntity;
import com.cinema.entity.UserEntity;
import com.cinema.repository.RoleRepository;
import com.cinema.repository.UserRepository;
import com.cinema.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenUtil;

    @Override
    public void registerUser(SignupRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new AlreadyExistsException("User with email [" + request.getEmail() + "] already exists");
        }

        String password = request.getPassword();
        System.out.println("Password: " + password);

        String encPassword = passwordEncoder.encode(password);
        System.out.println("Enc Password: " + encPassword);

        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(encPassword);
        userEntity.setDeleted(false);
        userEntity.setImage("logo.png");
        userEntity.setSexType(request.getSexType());

        RoleEntity roleEntity = roleRepository.findByRoleIgnoreCase("USER")
                .orElseThrow(() -> new NotFoundException("Role not found"));
        userEntity.setRoles(Arrays.asList(roleEntity));

        userRepository.save(userEntity);
    }

    @Override
    public String signin(SigninRequest request) {
        final Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        UserEntity userEntity = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found by email [" + request.getEmail() + "]"));
        userEntity.setLastLogin(LocalDateTime.now());
        userRepository.save(userEntity);

        return token;
    }

    @Override
    public Boolean checkTokenExpired(String token) {
        return jwtTokenUtil.isTokenExpired(token);
    }
}