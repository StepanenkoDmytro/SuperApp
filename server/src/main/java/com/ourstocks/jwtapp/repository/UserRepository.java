package com.ourstocks.jwtapp.repository;

import com.ourstocks.jwtapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String name);
    Optional<User> findByEmail(String email);
}
