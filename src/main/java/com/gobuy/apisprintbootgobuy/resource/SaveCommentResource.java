package com.gobuy.apisprintbootgobuy.resource;

import com.gobuy.apisprintbootgobuy.domain.model.User;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCommentResource {
    @NotNull
    private User user;
    @NotNull
    @Lob
    @Size(max = 245)
    private String message;

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
