package com.ourstocks.jwtapp.rest;

import com.ourstocks.jwtapp.dto.postsDTO.CommentDTO;
import com.ourstocks.jwtapp.dto.postsDTO.PostDTO;
import com.ourstocks.jwtapp.dto.postsDTO.RequestPostDTO;
import com.ourstocks.jwtapp.model.Post;
import com.ourstocks.jwtapp.model.PostComment;
import com.ourstocks.jwtapp.model.Status;
import com.ourstocks.jwtapp.repository.UserRepository;
import com.ourstocks.jwtapp.security.jwt.JwtUser;
import com.ourstocks.jwtapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ourstocks.jwtapp.model.User;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/blog/")
public class BlogRestControllerV1 {
    private final PostService postService;
    private final UserRepository userRepository;

    @Autowired
    public BlogRestControllerV1(PostService postService,
                                UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<Post> posts = postService.getAllPosts();
        if(posts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PostDTO> postDTOList = posts.stream().map(post ->
            PostDTO.fromPost(post)).collect(Collectors.toList());
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> addPost(@AuthenticationPrincipal JwtUser jwtUser,
                                        @RequestBody @Valid RequestPostDTO postDTO){
        User user = userRepository.findByUsername(jwtUser.getUsername());
        Post post = postDTO.toPost();
        post.setAuthor(user);
        postService.savePost(post);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPostById(@PathVariable(name = "id") Long id){
        Post post = postService.findPostById(id);
        if(post == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> updatePost(@PathVariable(name = "id") Long id,
                                           @RequestBody @Valid RequestPostDTO postDTO){
        Post post = postService.findPostById(id);
        if(post == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        post = postDTO.toPost(post);
        postService.savePost(post);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id){
        if(!postService.existsPostById(id)){
            return new ResponseEntity<>("Post not found",HttpStatus.BAD_REQUEST);
        }
        postService.deletePost(id);
        String message = "Post with id: " + id + " successfully deleted";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/addcomment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> addCommentToPost(@AuthenticationPrincipal JwtUser jwtUser,
                                                 @PathVariable(name = "id") Long id,
                                                 @RequestBody CommentDTO commentDTO){
        Post post = postService.findPostById(id);
        if(!postService.existsPostById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User user = userRepository.findByUsername(jwtUser.getUsername());
        PostComment comment = new PostComment(commentDTO.getComment());
        comment.setStatus(Status.ACTIVE);
        comment.setAuthor(user);
        post.addCommentToPost(comment);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
}
