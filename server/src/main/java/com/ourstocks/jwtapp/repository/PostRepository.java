package com.ourstocks.jwtapp.repository;

import com.ourstocks.jwtapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByTitle(String title);
}
