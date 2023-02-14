package com.ourstocks.jwtapp.service;



import com.ourstocks.jwtapp.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    void savePost(Post post);
    Post findPostById(long id);
    Post findPostByTitle(String title);
    void deletePost(long id);
    boolean existsPostById(long id);
}
