package com.gobuy.apisprintbootgobuy.resource;

import com.gobuy.apisprintbootgobuy.domain.model.User;

public class CommentResource {
    private Long id;
    private User user;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
