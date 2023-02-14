package com.ourstocks.jwtapp.service;

import com.ourstocks.jwtapp.dto.usersDTO.SignUpDTO;
import com.ourstocks.jwtapp.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();
    User findByEmail(String email);

    User findByUsername(String username);

    User findById(Long id);
    void update(User user);

    void delete(Long id);

    boolean existsByUsername(SignUpDTO user);
    boolean existsByEmail(SignUpDTO user);
}
