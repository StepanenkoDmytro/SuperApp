package com.example.MyTestSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/") //пишемо який саме зараз URL ми оброблюємо
    public String home(Model model) {
        model.addAttribute("title", "Головна сторінка");
        /*
        Отримуючі як параметр model, ми в ньому вказуємо які параметри ми хочемо передати у шаблон
        передаємо параметр "title" зі значенням "Головна сторінка", ще раз
        у середині шаблону ми зможемо звернуться к свойству title і вивести його значення
        Ми передаємо якись дані в шаблон і далі виклаємо шаблон по його назві
         */
        return "home";//головна ідея в конці визвати визначений шаблон
    }
    @GetMapping("/stocks") //для відстеження нового URL
    public String stocksMain(Model model) {
        return "stocks";
    }
}
/*
Котролери відповідають за обробку всіх переходів на сайті
Кожна функція оброблює якусь URL адресу
 */
