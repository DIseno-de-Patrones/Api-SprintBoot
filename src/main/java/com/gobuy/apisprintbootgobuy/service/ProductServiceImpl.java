package com.gobuy.apisprintbootgobuy.service;

import com.gobuy.apisprintbootgobuy.domain.model.Product;
import com.gobuy.apisprintbootgobuy.domain.repository.ProductRepository;
import com.gobuy.apisprintbootgobuy.domain.service.ProductService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product product1 = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        product1.setName(product.getName());
        product1.setProductPhoto(product.getProductPhoto());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        return productRepository.save(product1);    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
        productRepository.delete(product);
        return ResponseEntity.ok().build();    }
}
