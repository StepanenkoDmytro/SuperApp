package com.ourstocks.jwtapp.repository;

import com.ourstocks.jwtapp.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
