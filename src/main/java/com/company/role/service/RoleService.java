package com.company.role.service;


import com.company.role.model.Role;
import com.company.role.type.RoleType;

public interface RoleService {

    void createRole(Role role);

    Role findByName(RoleType roleTypeName);

    void createRoles();
}
