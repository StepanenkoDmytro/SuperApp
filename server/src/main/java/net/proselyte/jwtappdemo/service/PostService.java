package net.proselyte.jwtappdemo.service;


import net.proselyte.jwtappdemo.dto.PostDto;
import net.proselyte.jwtappdemo.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post register(Post post);

    List<Post> getAll();

    Post findPostByTitle(String title);

    Optional<Post> findPostById(Long id);

    void deletePost(Long id);

    boolean existsPostByTitle(PostDto user);
    boolean existsPostById(Long id);
}
