package com.ourstocks.jwtapp.dto.postsDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ourstocks.jwtapp.model.Post;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDTO {
    private String title;
    private String anons;

    public static PostDTO fromPost(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setAnons(post.getAnons());
        return postDTO;
    }
}
