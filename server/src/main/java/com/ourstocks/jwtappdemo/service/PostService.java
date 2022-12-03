package com.ourstocks.jwtappdemo.service;


import com.ourstocks.jwtappdemo.dto.PostDto;
import com.ourstocks.jwtappdemo.model.Post;

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
