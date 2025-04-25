package com.sample.prototype.domain.comment.repository;

import com.sample.prototype.domain.comment.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
