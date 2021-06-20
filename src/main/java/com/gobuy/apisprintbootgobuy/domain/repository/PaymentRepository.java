package com.gobuy.apisprintbootgobuy.domain.repository;

import com.gobuy.apisprintbootgobuy.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    public Optional<Payment> findById(Long id);
}
