package com.ourstocks.jwtapp.service;

import com.ourstocks.jwtapp.dto.SignUpDto;
import com.ourstocks.jwtapp.model.User;

import java.util.List;

/**
 * Service interface for class {@link User}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    boolean existsByUsername(SignUpDto user);
    boolean existsByEmail(SignUpDto user);
}