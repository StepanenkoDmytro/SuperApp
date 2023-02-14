package com.ourstocks.jwtapp.service;


import com.ourstocks.jwtapp.dto.PostDto;
import com.ourstocks.jwtapp.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post register(Post post);

    List<Post> getAll();

    Post findPostByTitle(String title);

    Post findPostById(Long id);

    void deletePost(Long id);

    boolean existsPostByTitle(PostDto user);
    boolean existsPostById(Long id);
}
