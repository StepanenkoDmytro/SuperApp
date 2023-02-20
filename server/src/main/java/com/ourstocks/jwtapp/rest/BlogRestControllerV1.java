package com.ourstocks.jwtapp.rest;

import com.ourstocks.jwtapp.dto.postsDTO.CommentDTO;
import com.ourstocks.jwtapp.dto.postsDTO.PostDTO;
import com.ourstocks.jwtapp.dto.postsDTO.RequestPostDTO;
import com.ourstocks.jwtapp.model.*;
import com.ourstocks.jwtapp.repository.PostCommentRepository;
import com.ourstocks.jwtapp.repository.UserFollowsRepository;
import com.ourstocks.jwtapp.repository.UserRepository;
import com.ourstocks.jwtapp.security.jwt.JwtUser;
import com.ourstocks.jwtapp.service.PostService;
import com.ourstocks.jwtapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/blog/")
@Api(value = "Rest controller для роботи з розділом Блог")
@CrossOrigin
public class BlogRestControllerV1 {
    private final PostService postService;
    private final UserService userService;
    private final PostCommentRepository postCommentRepository;
    private final UserFollowsRepository userFollowsRepository;

    @Autowired
    public BlogRestControllerV1(PostService postService,
                                UserService userService,
                                PostCommentRepository postCommentRepository,
                                UserFollowsRepository userFollowsRepository) {
        this.postService = postService;
        this.userService = userService;
        this.postCommentRepository = postCommentRepository;
        this.userFollowsRepository = userFollowsRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Отримання всіх постів з БД, незалежно від прав юзера")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<Post> posts = postService.getAllPosts();
        if(posts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PostDTO> postDTOList = posts.stream().map(PostDTO::fromPost).collect(Collectors.toList());
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Додати новий пост")
    public ResponseEntity<Post> addPost(@AuthenticationPrincipal JwtUser jwtUser,
                                        @RequestBody @Valid RequestPostDTO postDTO){
        User user = userService.findByUsername(jwtUser.getUsername());
        Post post = postDTO.toPost();
        user.addPostToUser(post);
        postService.savePost(post);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Отримати конкретний пост, але доступ є тільки у підтвердженого від автора підписника")
    public ResponseEntity<Post> getPostById(@PathVariable(name = "id") Long id,
                                            @AuthenticationPrincipal JwtUser jwtUser) {
        Post post = postService.findPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User author = userService.findByUsername(post.getAuthor().getUsername());
        User observer = userService.findByUsername(jwtUser.getUsername());
        Set<Long> postPermission = userFollowsRepository.findAllByDistributor(author.getId())
                .stream().map(UserFollows::getSubscriber).collect(Collectors.toSet());

        postPermission.add(author.getId());
        if (!postPermission.contains(observer.getId())) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Оновити дані посту, тільки якщо ти автор посту")
    public ResponseEntity<Post> updatePost(@AuthenticationPrincipal JwtUser jwtUser,
                                           @PathVariable(name = "id") Long id,
                                           @RequestBody @Valid RequestPostDTO postDTO){
        Post post = postService.findPostById(id);
        if(post == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!jwtUser.getEmail().equals(post.getAuthor().getEmail())){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        post = postDTO.toPost(post);
        postService.savePost(post);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Видалити пост, тільки якщо ти автор посту")
    public ResponseEntity<String> deletePost(@AuthenticationPrincipal JwtUser jwtUser,
                                             @PathVariable(name = "id") Long id){
        if(!postService.existsPostById(id)){
            return new ResponseEntity<>("Post not found",HttpStatus.BAD_REQUEST);
        }
        if(!jwtUser.getEmail().equals(postService.findPostById(id).getAuthor().getEmail())){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        postService.deletePost(id);
        String message = "Post with id: " + id + " successfully deleted";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/addcomment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Додати коментар до посту")
    public ResponseEntity<Post> addCommentToPost(@AuthenticationPrincipal JwtUser jwtUser,
                                                 @PathVariable(name = "id") Long id,
                                                 @RequestBody CommentDTO commentDTO){
        Post post = postService.findPostById(id);
        if(!postService.existsPostById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User user = userService.findByUsername(jwtUser.getUsername());
        PostComment comment = new PostComment(commentDTO.getComment());
        Date date = new Date();
        comment.setCreated(date);
        comment.setUpdated(date);
        comment.setStatus(Status.ACTIVE);
        comment.setAuthor(user);
        post.addCommentToPost(comment);
        postService.savePost(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @RequestMapping(value = "{id}/{id_comment}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Видалити коментар, тільки якщо ти автор коментаря")
    public ResponseEntity<String> deleteCommentFromPost(@AuthenticationPrincipal JwtUser jwtUser,
                                                        @PathVariable(name = "id_comment") Long id_comment){
        PostComment comment = postCommentRepository.findById(id_comment).orElse(null);
        if(comment == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if(!jwtUser.getEmail().equals(comment.getAuthor().getEmail())){
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        String response = "Comment from post " + comment.getPost()
                + " with id" + id_comment + " successfully deleted";
        postCommentRepository.deleteById(id_comment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
