package com.gobuy.apisprintbootgobuy.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SavePublicationResource {

    @NotNull
    private String publishAt;

    @NotNull
    @Lob
    @Size(max = 245)
    private String description;

    @NotNull
    private String photo;

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
