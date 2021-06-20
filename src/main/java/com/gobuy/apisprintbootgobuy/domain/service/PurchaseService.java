package com.gobuy.apisprintbootgobuy.domain.service;

import com.gobuy.apisprintbootgobuy.domain.model.Purchase;
import org.springframework.http.ResponseEntity;

public interface PurchaseService {
    Purchase getPurchaseById(Long purchaseId);
    ResponseEntity<?> deletePurchase(Long purchaseId);
    Purchase createPurchase(Purchase purchase);
    Purchase updatePurchase(Long purchaseId,Purchase purchase);
}
