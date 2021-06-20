package com.gobuy.apisprintbootgobuy.domain.service;

import com.gobuy.apisprintbootgobuy.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    Page<Role> getAllRoles(Pageable pageable);
    Role getRoleById(Long roleId);
    Role updateRole(Long roleId, Role roleRequest);
    ResponseEntity<?> deleteRole(Long roleId);
    Role getRoleByName(String name);
    Role createRole(Role role);
}
