package com.gobuy.apisprintbootgobuy.resource;

import java.util.Date;

public class PurchaseResource {
    private Long id;
    private Date delivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }
}
