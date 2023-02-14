package com.ourstocks.jwtapp.service.impl;

import com.ourstocks.jwtapp.dto.PostDto;
import com.ourstocks.jwtapp.model.Post;
import com.ourstocks.jwtapp.model.Status;
import com.ourstocks.jwtapp.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import com.ourstocks.jwtapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post register(Post post) {
        post.setStatus(Status.ACTIVE);
        Post registerPost = postRepository.save(post);
        log.info("IN register - post: {} successfully added", registerPost);
        return registerPost;
    }

    @Override
    public List<Post> getAll() {
        List<Post> result = postRepository.findAll();
        log.info("IN getAll - post: {} posts found", result.size());
        return result;
    }

    @Override
    public Post findPostByTitle(String title) {
        Post post = postRepository.findPostByTitle(title);
        log.info("IN findPostByTitle - post: {} found by title: {}", post, title);
        return post;
    }

    @Override
    public Post findPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        log.info("IN findPostById - post: {} found by id: {}", post, id);
        return post;
    }

    @Override
    public void deletePost(@PathVariable(value = "id") Long id) {
        postRepository.deleteById(id);
        log.info("IN deletePost - post with id: {} successfully deleted", id);
    }

    @Override
    public boolean existsPostByTitle(PostDto post) {
        if (postRepository.existsPostByTitle(post.getTitle())) {
            return true;
        }
        return false;
    }
    @Override
    public boolean existsPostById(Long id) {
        if (postRepository.existsPostById(id)) {
            return true;
        }
        return false;
    }
}
