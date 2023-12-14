package com.company.role.repository;

import com.company.role.model.Role;
import com.company.role.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing role data.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleType roleName);
}
