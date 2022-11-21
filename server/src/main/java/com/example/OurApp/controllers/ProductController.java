package com.example.OurApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //контролює наше звернення (як користувача) до застосунку (http запроси)
public class ProductController {
    @GetMapping("/")
    public String products() {
        return "products"; //назва представлення в тілі документу
    }
}
