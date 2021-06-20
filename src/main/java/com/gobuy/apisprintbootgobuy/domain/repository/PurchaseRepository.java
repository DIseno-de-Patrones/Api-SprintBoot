package com.gobuy.apisprintbootgobuy.domain.repository;

import com.gobuy.apisprintbootgobuy.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    public Optional<Purchase> findById(Long id);
}
