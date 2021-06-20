package com.gobuy.apisprintbootgobuy.domain.repository;

import com.gobuy.apisprintbootgobuy.domain.model.Role;
import com.gobuy.apisprintbootgobuy.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public Page<User> findAllByRole(Role role, Pageable pageable);
    public Optional<User> findById(Long id);

}
