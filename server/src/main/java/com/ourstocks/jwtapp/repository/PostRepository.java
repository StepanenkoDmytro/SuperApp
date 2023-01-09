package com.ourstocks.jwtapp.repository;

import com.ourstocks.jwtapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostByTitle(String title);
    boolean existsPostByTitle(String title);
    boolean existsPostById(Long id);
}

