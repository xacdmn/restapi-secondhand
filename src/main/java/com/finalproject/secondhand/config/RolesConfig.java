package com.finalproject.secondhand.config;

import com.finalproject.secondhand.Enum.Role;
import com.finalproject.secondhand.entity.Roles;
import com.finalproject.secondhand.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfig {
    private static final Logger LOG = LoggerFactory.getLogger(RolesConfig.class);

    RolesConfig(RoleRepository roleRepository){
        LOG.info("Test RolesConfig run");
        for (Role c : Role.values()){
            try {
                Roles roles = roleRepository.findByName(c)
                        .orElseThrow(() -> new RuntimeException("Roles NotFound"));
                LOG.info("Role {} has been found!", roles.getName());
            }catch (RuntimeException rte){
                LOG.info("Roles {} is not found, inserting to DB ....", c.name());
                Roles roles = new Roles();
                roles.setName(c);
                roleRepository.save(roles);
            }
        }
    }
}
