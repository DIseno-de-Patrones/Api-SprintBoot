package com.gobuy.apisprintbootgobuy.domain.service;

import com.gobuy.apisprintbootgobuy.domain.model.Comment;
import com.gobuy.apisprintbootgobuy.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Page<Comment>getAllComment(Pageable pageable);
    Comment getCommentById(Long commentId);
    Comment updateComment(Long commentId,Comment comment);
    ResponseEntity<?>deleteRole(Long commentId);
    Comment getCommentByUser(User user);
}
