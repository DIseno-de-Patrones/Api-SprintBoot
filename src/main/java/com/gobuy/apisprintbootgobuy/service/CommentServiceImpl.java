package com.gobuy.apisprintbootgobuy.service;

import com.gobuy.apisprintbootgobuy.domain.model.Comment;
import com.gobuy.apisprintbootgobuy.domain.model.User;
import com.gobuy.apisprintbootgobuy.domain.repository.CommentRepository;
import com.gobuy.apisprintbootgobuy.domain.service.CommentService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> getAllComment(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
    }

    @Override
    public Comment updateComment(Long commentId, Comment comment) {
        Comment comment1 = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        comment1.setMessage(comment.getMessage());
        comment1.setUser(comment.getUser());
        return commentRepository.save(comment1);
    }

    @Override
    public ResponseEntity<?> deleteRole(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepository.delete(comment);
        return ResponseEntity.ok().build();    }

    @Override
    public Comment getCommentByUser(User user) {
        return commentRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("Comment","user",user));    }
}
