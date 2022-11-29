package com.example.MyTestSpring.repositories;

import com.example.MyTestSpring.models.Post;
import org.springframework.data.repository.CrudRepository;

/*
CRUD - Create, r, update, delete
 */
public interface PostRepository extends CrudRepository<Post, Long> {
}
