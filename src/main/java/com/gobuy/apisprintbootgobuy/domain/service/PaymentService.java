package com.gobuy.apisprintbootgobuy.domain.service;

import com.gobuy.apisprintbootgobuy.resource.SavePaymentResource;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentService {
    public PaymentIntent paymentIntent(SavePaymentResource paymentResource) throws StripeException;
    public PaymentIntent confirmPaymentIntent(String id) throws StripeException;
    public PaymentIntent cancelPaymentIntent(String id) throws StripeException;
}
