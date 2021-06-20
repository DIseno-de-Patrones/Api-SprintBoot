package com.gobuy.apisprintbootgobuy.domain.repository;

import com.gobuy.apisprintbootgobuy.domain.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
    public Optional<Publication> findById(Long id);
}
