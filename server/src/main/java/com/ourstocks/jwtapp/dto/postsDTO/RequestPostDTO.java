package com.ourstocks.jwtapp.dto.postsDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ourstocks.jwtapp.model.Post;
import com.ourstocks.jwtapp.model.Status;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//public class PostDto implements Serializable {
public class RequestPostDTO {
    @NotBlank(message = "Post's title cannot be empty.")
    private String title;
    private String anons;
    @NotBlank(message = "Post's text cannot be empty.")
    private String fullText;

    public Post toPost(){
        Post post = new Post();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);

        Date date = new Date();
        post.setCreated(date);
        post.setUpdated(date);

        post.setStatus(Status.ACTIVE);

        return post;
    }

    public static RequestPostDTO fromPost(Post post){
        RequestPostDTO postDTO = new RequestPostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setAnons(post.getAnons());
        postDTO.setFullText(post.getFullText());
        return postDTO;
    }

    public Post toPost(Post post){
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);

        Date date = new Date();
        post.setUpdated(date);

        return post;
    }
}
