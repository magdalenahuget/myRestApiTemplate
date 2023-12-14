package com.company.user.repository;

import com.company.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByRolesIn(Set<Long> roles);

    Optional<User> findByName(String username);

    Optional<User> findByEmail(String email);
}