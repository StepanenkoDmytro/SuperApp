package com.ourstocks.jwtapp.dto.usersDTO;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
