package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.enums.ERole;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RoleService {
    Optional<Roles> findByRole(ERole role);

    List<Roles> findAll();

    void save(Roles role1);
}
