package com.gobuy.apisprintbootgobuy.service;

import com.gobuy.apisprintbootgobuy.domain.model.Purchase;
import com.gobuy.apisprintbootgobuy.domain.repository.PurchaseRepository;
import com.gobuy.apisprintbootgobuy.domain.service.PurchaseService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase getPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase", "Id", purchaseId));    }

    @Override
    public ResponseEntity<?> deletePurchase(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase", "Id", purchaseId));
        purchaseRepository.delete(purchase);
        return ResponseEntity.ok().build();    }

    @Override
    public Purchase createPurchase(Purchase purchase) {
        return null;
    }

    @Override
    public Purchase updatePurchase(Long purchaseId, Purchase purchase) {
        Purchase purchase1 = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase", "Id", purchaseId));
        purchase1.setDelivery(purchase.getDelivery());
        return purchaseRepository.save(purchase1);    }
}
