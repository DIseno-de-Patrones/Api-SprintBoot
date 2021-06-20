package com.gobuy.apisprintbootgobuy.domain.service;

import com.gobuy.apisprintbootgobuy.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    Page<User> getAllUsersByRoleId(Long roleId, Pageable pageable);
    Page<User> getAllUsersBySubscriptionId(Long subscriptionId, Pageable pageable);
    User assignRoleById(User user, Long roleId);
    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long userId, User userDetails);
    ResponseEntity<?> deleteUser(Long userId);
}
