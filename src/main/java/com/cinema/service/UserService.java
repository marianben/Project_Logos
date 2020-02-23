package com.cinema.service;

import com.cinema.dto.UserDTO;
import com.cinema.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    void createUser(UserDTO userDto);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    void deleteUser(Long id);

    Page<UserEntity> getUsersByPage(Pageable pageable);

    void addImageToUser(Long id, String fileName);
}
