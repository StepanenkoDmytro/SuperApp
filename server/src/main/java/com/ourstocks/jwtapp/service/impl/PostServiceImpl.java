package com.ourstocks.jwtapp.service.impl;

import com.ourstocks.jwtapp.model.Post;
import com.ourstocks.jwtapp.repository.PostRepository;
import com.ourstocks.jwtapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Post> getAllPosts() {
        List<Post> result = postRepository.findAll();
        log.info("IN getAll - {} posts found", result.size());
        return result;
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
        log.info("IN savePost - post: {} successfully saved", post);
    }

    @Override
    public Post findPostById(long id) {
        Post post = null;
        Optional<Post> optional = postRepository.findById(id);
        if(optional.isPresent()){
            post = optional.get();
        }else{
            log.warn("IN getPost - no post found by id: {}", id);
        }
        log.info("IN getPost - post: {} found by id: {}", post, id);
        return post;
    }

    @Override
    public Post findPostByTitle(String title) {
        Post post = postRepository.findByTitle(title).orElse(null);
        if(post == null){
            log.warn("IN findPostByTitle - no post found by title: {}", title);
            return null;
        }
        log.info("IN findPostByTitle - post: {} found by title: {}", post, title);
        return post;
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null){
            log.warn("IN deletePost - no post found by id: {}", id);
        }
        postRepository.deleteById(id);
        log.info("IN deletePost - post with id: {} successfully deleted", id);
    }

    @Override
    public boolean existsPostById(long id) {
        return postRepository.existsById(id);
    }

//    @Override
//    public List<Post> findAllByName(String name) {
//        List<Post> employees = postRepository.findAllByName(name);
//        return employees;
//    }
}
