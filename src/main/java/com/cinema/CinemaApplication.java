package com.cinema;

import com.cinema.entity.RoleEntity;
import com.cinema.entity.UserEntity;
import com.cinema.repository.RoleRepository;
import com.cinema.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(
                CinemaA
                pplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            RoleEntity roleAdmin = new RoleEntity();
            roleAdmin.setRole("ADMIN");

            RoleEntity roleUser = new RoleEntity();
            roleUser.setRole("USER");
            roleRepository.saveAll(Arrays.asList(roleAdmin, roleUser));
        }

        if (userRepository.count() == 0) {

            RoleEntity roleAdmin = roleRepository.findByRoleIgnoreCase("ADMIN").get();
            RoleEntity roleUser = roleRepository.findByRoleIgnoreCase("USER").get();

            UserEntity adminEntity = new UserEntity();
            adminEntity.setEmail("admin@gmail.com");
            adminEntity.setPassword(passwordEncoder.encode("123"));
            adminEntity.setName("Admin");
            adminEntity.setDeleted(false);
            adminEntity.setSexType("MALE");
            adminEntity.setRoles(Arrays.asList(roleAdmin));
            userRepository.save(adminEntity);


            for (int i = 0; i < 50; i++) {
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail("user" + i + "@gmail.com");
                userEntity.setPassword(passwordEncoder.encode("123"));
                userEntity.setName("User" + i);
                userEntity.setDeleted(false);
                userEntity.setSexType("MALE");
                userEntity.setRoles(Arrays.asList(roleUser));
                userRepository.save(userEntity);
            }
        }
    }
}


