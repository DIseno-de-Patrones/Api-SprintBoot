package com.gobuy.apisprintbootgobuy.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveProductResource {
    @NotNull
    @Size(max = 50)
    private String name;
    @NotNull
    private Long price;

    @NotNull
    @Lob
    @Size(max = 245)
    private String description;
    @NotNull
    private String productPhoto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }
}
