package com.ourstocks.jwtapp.repository;

import com.ourstocks.jwtapp.model.СommentPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentPostService extends JpaRepository<СommentPost,Long> {
}
