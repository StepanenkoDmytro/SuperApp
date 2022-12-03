package com.ourstocks.jwtappdemo.repository;

import com.ourstocks.jwtappdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Role}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
