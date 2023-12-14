package com.company.role.service;

import com.company.role.model.Role;
import com.company.role.repository.RoleRepository;
import com.company.role.type.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for managing roles in the system.
 */
@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(RoleType roleTypeName){
        return roleRepository.findByName(roleTypeName);
    }

    /**
     * Creates default roles in the system.
     */
    @Override
    public void createRoles() {
        for (RoleType roleType : RoleType.values()) {
            Role role = new Role(roleType);
            roleRepository.save(role);
        }
    }
}
