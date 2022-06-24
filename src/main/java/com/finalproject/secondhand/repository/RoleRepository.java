package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRole(ERole role);
}
