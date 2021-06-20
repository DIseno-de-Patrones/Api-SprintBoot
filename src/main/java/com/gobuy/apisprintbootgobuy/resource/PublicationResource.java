package com.gobuy.apisprintbootgobuy.resource;

import com.gobuy.apisprintbootgobuy.domain.model.AuditModel;

public class PublicationResource extends AuditModel {
    private Long id;
    private String publishAt;
    private String description;
    private String photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(String publishAt) {
        this.publishAt = publishAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
