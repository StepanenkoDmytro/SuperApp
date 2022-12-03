package com.ourstocks.jwtappdemo.rest;

import com.ourstocks.jwtappdemo.dto.PostDto;
import com.ourstocks.jwtappdemo.model.Post;
import com.ourstocks.jwtappdemo.repository.PostRepository;
import com.ourstocks.jwtappdemo.service.PostService;
import com.ourstocks.jwtappdemo.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts/v1/")
public class PostRestControllerV1 {

    private PostRepository postRepository;
    private PostService postService;

    @Autowired
    public PostRestControllerV1(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @GetMapping("")//
    public List<Post> getAll(){
        return postService.getAll();
    }

    @PostMapping(path = "add")//
    public ResponseEntity postAdd(@RequestBody PostDto postDTO) {
        String title = postDTO.getTitle();
        String full_text = postDTO.getFull_text();
        String small_text = postDTO.getSmall_text();
        if (title == null | full_text == null | small_text == null ){
            return ResponseEntity.badRequest().body("Incorrect data");
        }
        Post post = new Post();
        post.setTitle(title);
        post.setFull_text(full_text);
        post.setSmall_text(small_text);
        postService.register(post);
        return ResponseEntity.ok("Post added successfully");
    }

    @GetMapping("{id}")//
    Post one(@PathVariable Long id) {

        return postService.findPostById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @PatchMapping("{id}")
    public Optional<Post> postDetails(@RequestBody PostDto postDto, @PathVariable(value = "id") long id) {
        if(!postService.existsPostByTitle(postDto) | !postService.existsPostById(id)) {
            throw new PostNotFoundException(id);
        }
        Optional<Post> postOptional = postService.findPostById(id);
        return postOptional.map(post -> {
            post.setTitle(postDto.getTitle());
            post.setFull_text(postDto.getFull_text());
            post.setSmall_text(postDto.getSmall_text());
            return postService.register(post);
        });
    }

    @DeleteMapping("{id}")//
    public ResponseEntity deletePost(@PathVariable(value = "id") Long id) {
        postRepository.deleteById(id);
        if(postService.existsPostById(id)) {
            return ResponseEntity.badRequest().body("Failed to delete. Post not exist");
        }
        return ResponseEntity.ok("Post deleted successfully");
    }
}