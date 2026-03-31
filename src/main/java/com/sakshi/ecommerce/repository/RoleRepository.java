package com.sakshi.ecommerce.repository;

import com.sakshi.ecommerce.entity.Role;
// JpaRepository provides basic CRUD operations for Role entity
import org.springframework.data.jpa.repository.JpaRepository;
// Optional is a container object which may or may not contain a non-null value. It is used to avoid null checks and NullPointerExceptions.
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // find role by name
    Optional<Role> findByNameIgnoreCase(String name);
}