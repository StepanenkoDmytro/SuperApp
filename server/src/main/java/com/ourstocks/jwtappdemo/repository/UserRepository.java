package com.ourstocks.jwtappdemo.repository;

import com.ourstocks.jwtappdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String name);
}
