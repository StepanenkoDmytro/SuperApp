package com.example.MyTestSpring.controllers;

import com.example.MyTestSpring.models.Post;
import com.example.MyTestSpring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {
    @Autowired //для створення зміної, яка посилається на репозиторій
    private PostRepository postRepository;

    @GetMapping("/posts")
    /*
    для відстеження нового URL
    GetMapping - коли користувач просто ПЕРЕХОДИТЬ по заданій адресі
     */
    public String postMain(Model model){
        Iterable<Post>  posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/add")
    public String postsAdd(Model model) {
        return "posts-add";
    }

    @PostMapping("/posts/add")
    /*
    Post дані
    Ми получили дані з форми(форма додавання статті)
     */
    public String postAdd(@RequestParam String title, @RequestParam String full_text, @RequestParam String small_text, Model model) { //отрумую саме дані з форми html
        /*
        RequestParam
         */
        Post post = new Post(title, full_text, small_text); //створили наш обʼєкт
        postRepository.save(post); //та зберегли його, через наш інтерфейс для роботи з БД
        return "redirect:/posts"; //і з получених даних ми повертаємо
    }

    @GetMapping("/posts/{id}")
    public String postDetails(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)) {
            return "redirect:/posts";
        }
        /*
        Щоб брати динамічне значення з URL адреси в {} ми використовуємо
        анотацію PathVariable, а в () вказуємо який динамічний параметр ми приймаємо
        !Може бути не один динамічний параметр
         */
        Optional<Post> post = postRepository.findById(id);
//        краще не передавати Optional в шаблон, тому
        List<Post> list = new ArrayList<>();
        post.ifPresent(list::add);
        model.addAttribute("post", list);
        return "post-details";
    }

    @GetMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long id, Model model) {

        Optional<Post> post = postRepository.findById(id);
        List<Post> list = new ArrayList<>();
        post.ifPresent(list::add);
        model.addAttribute("post", list);
        return "post-edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String postUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String full_text, @RequestParam String small_text, Model model) { //отрумую саме дані з форми html
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setFull_text(full_text);
        post.setSmall_text(small_text);
        postRepository.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/remove")
    public String postDelete(@PathVariable(value = "id") long id, Model model) { //отрумую саме дані з форми html
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/posts";
    }
}
