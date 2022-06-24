package com.finalproject.secondhand.config;

import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.enums.ERole;
import com.finalproject.secondhand.service.RoleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

public class PopulateRolesConfig {

    @Autowired
    private RoleServiceImpl roleService;

    private static final Logger logger = LoggerFactory.getLogger(PopulateRolesConfig.class);

    @Bean
    public void prerun() {
        for (ERole role : ERole.values()) {
            Optional<Roles> dbRole = roleService.findByRole(role);
            if (!dbRole.isPresent()) {
                logger.info("Role " + role.name() + " is not found, inserting to DB . . .");
                Roles role1 = new Roles();
                role1.setRole(role);
                roleService.save(role1);
                logger.info(roleService.findAll().toString());
            }
        }
    }
}
