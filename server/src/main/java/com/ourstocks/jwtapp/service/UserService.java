package com.ourstocks.jwtapp.service;

import com.ourstocks.jwtapp.dto.SignUpDto;
import com.ourstocks.jwtapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(User user);

    List<User> getAll();
    User findByEmail(String email);

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    boolean existsByUsername(SignUpDto user);
    boolean existsByEmail(SignUpDto user);
}
