package com.ourstocks.jwtappdemo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostDto implements Serializable {

    private String title;
    private String small_text;
    private String full_text;
}
