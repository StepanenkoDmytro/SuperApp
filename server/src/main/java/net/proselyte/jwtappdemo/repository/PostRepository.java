package net.proselyte.jwtappdemo.repository;

import net.proselyte.jwtappdemo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostByTitle(String title);
    boolean existsPostByTitle(String title);
    boolean existsPostById(Long id);
}

