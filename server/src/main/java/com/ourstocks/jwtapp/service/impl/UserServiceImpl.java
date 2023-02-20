package com.ourstocks.jwtapp.service.impl;

import com.ourstocks.jwtapp.dto.usersDTO.SignUpDTO;
import com.ourstocks.jwtapp.model.Role;
import com.ourstocks.jwtapp.model.Status;
import com.ourstocks.jwtapp.model.User;
import com.ourstocks.jwtapp.repository.RoleRepository;
import com.ourstocks.jwtapp.repository.UserRepository;
import com.ourstocks.jwtapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(user.getPassword());
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email).orElse(null);
        log.info("IN findByEmail - user: {} found by email: {}", result, email);
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
        log.info("IN update - user: {} successfully updated", user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("IN delete - no user found by id: {}", id);
        } else {
            user.setStatus(Status.DELETED);
            log.info("IN delete - user with id: {} successfully change status on DELETED", id);
        }
    }

    @Override
    public boolean existsByUsername(SignUpDTO user) {
        return userRepository.existsByUsername(user.getUsername());
    }

    @Override
    public boolean existsByEmail(SignUpDTO user) {
        return userRepository.existsByEmail(user.getEmail());
    }
}
