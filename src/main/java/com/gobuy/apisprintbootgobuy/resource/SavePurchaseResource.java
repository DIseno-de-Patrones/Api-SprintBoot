package com.gobuy.apisprintbootgobuy.resource;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SavePurchaseResource {
    @NotNull
    private Date delivery;

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }
}
