package com.gobuy.apisprintbootgobuy.domain.service;

import com.gobuy.apisprintbootgobuy.domain.model.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Product getProductById(Long productId);
    Product createProduct(Product product);
    Product updateProduct(Long productId,Product product);
    ResponseEntity<?> deleteProduct(Long productId);
}
