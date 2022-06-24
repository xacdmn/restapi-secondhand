package com.finalproject.secondhand.service;

import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.enums.ERole;
import com.finalproject.secondhand.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;


    public Optional<Roles> findByRole(ERole role) {
        return roleRepository.findByRole(role);
    }

    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    public void save(Roles role) {
        roleRepository.save(role);
    }

}
