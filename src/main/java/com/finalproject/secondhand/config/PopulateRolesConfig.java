package com.finalproject.secondhand.config;

import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.enums.ERole;
import com.finalproject.secondhand.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

public class PopulateRolesConfig {

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PopulateRolesConfig.class);

    @Bean
    public void populateRoles() {
        for (ERole role : ERole.values()) {
            Optional<Roles> dbRole = roleRepository.findByRole(role);
            if (!dbRole.isPresent()) {
                LOGGER.info("Role " + role.name() + " is not found, inserting to DB . . .");
                Roles role1 = new Roles();
                role1.setRole(role);
                roleRepository.save(role1);
                LOGGER.info(roleRepository.findAll().toString());
            }
        }
    }
}
