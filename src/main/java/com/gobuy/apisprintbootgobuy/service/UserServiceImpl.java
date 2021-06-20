package com.gobuy.apisprintbootgobuy.service;

import com.gobuy.apisprintbootgobuy.domain.model.Role;
import com.gobuy.apisprintbootgobuy.domain.model.User;
import com.gobuy.apisprintbootgobuy.domain.repository.RoleRepository;
import com.gobuy.apisprintbootgobuy.domain.repository.UserRepository;
import com.gobuy.apisprintbootgobuy.domain.service.UserService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllUsersByRoleId(Long roleId, Pageable pageable) {
        return  null;
    }

    @Override
    public Page<User> getAllUsersBySubscriptionId(Long subscriptionId, Pageable pageable) {
        return null;
    }

    @Override
    public User assignRoleById(User user, Long roleId) {
        Role role=roleRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role","id",roleId));
        user.setRole(role);
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setName(userDetails.getName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setDescription(userDetails.getDescription());
        user.setProfilePhoto(userDetails.getProfilePhoto());
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
