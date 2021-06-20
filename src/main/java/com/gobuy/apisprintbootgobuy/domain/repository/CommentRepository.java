package com.gobuy.apisprintbootgobuy.domain.repository;

import com.gobuy.apisprintbootgobuy.domain.model.Comment;
import com.gobuy.apisprintbootgobuy.domain.model.Role;
import com.gobuy.apisprintbootgobuy.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    public Optional<Comment> findById(Long id);
    public Optional<Comment> findByUser(User user);

}
