package com.example.OurApp.controllers;

import com.example.OurApp.models.User;
import com.example.OurApp.repositories.UserRepository;
import com.example.OurApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("users", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Користувач з email: " + user.getEmail() + "вже існує");
            return "registration";
        }
        return "redirect:/login";
    }


}
