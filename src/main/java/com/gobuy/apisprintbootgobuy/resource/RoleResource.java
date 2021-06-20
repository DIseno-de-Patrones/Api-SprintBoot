package com.gobuy.apisprintbootgobuy.resource;

import com.gobuy.apisprintbootgobuy.domain.model.AuditModel;

public class RoleResource extends AuditModel {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
