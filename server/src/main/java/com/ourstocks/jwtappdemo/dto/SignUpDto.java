package com.ourstocks.jwtappdemo.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
