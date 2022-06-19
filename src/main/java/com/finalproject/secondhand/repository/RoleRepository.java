package com.finalproject.secondhand.repository;

import com.finalproject.secondhand.Enum.Role;
import com.finalproject.secondhand.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByName (Role name);
}
