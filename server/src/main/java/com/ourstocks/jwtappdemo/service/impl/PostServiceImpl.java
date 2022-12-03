package com.ourstocks.jwtappdemo.service.impl;

import com.ourstocks.jwtappdemo.dto.PostDto;
import com.ourstocks.jwtappdemo.model.Post;
import com.ourstocks.jwtappdemo.model.Status;
import com.ourstocks.jwtappdemo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import com.ourstocks.jwtappdemo.service.PostService;
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

    @Override//
    public Post register(Post post) {
        post.setStatus(Status.ACTIVE);
        Post registerPost = postRepository.save(post);

        log.info("IN register - post: {} successfully added", registerPost);

        return registerPost;
    }

    @Override//
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override//
    public Post findPostByTitle(String title) {
        return postRepository.findPostByTitle(title);
    }

    @Override//
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override//
    public void deletePost(@PathVariable(value = "id") Long id) {
        postRepository.deleteById(id);
    }

    @Override//
    public boolean existsPostByTitle(PostDto post) {
        if (postRepository.existsPostByTitle(post.getTitle())) {
            return true;
        }
        return false;
    }
    @Override//
    public boolean existsPostById(Long id) {
        if (postRepository.existsPostById(id)) {
            return true;
        }
        return false;
    }
}
