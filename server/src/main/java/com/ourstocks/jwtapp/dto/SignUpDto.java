package com.ourstocks.jwtapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignUpDto {
    @NotEmpty(message = "User's name cannot be empty.")
    @Size(min = 3, max = 100,
            message = "length should be between 3 to 100")
    private String username;
    @NotBlank(message = "User's first name cannot be empty.")
    private String firstName;
    @NotBlank(message = "User's last name cannot be empty.")
    private String lastName;
    @NotBlank(message = "User's password cannot be empty.")
    @Size(min = 7, message = "Password should be min 7 symbols")
    private String password;
    @NotBlank(message = "User's email cannot be empty.")
    private String email;
}
