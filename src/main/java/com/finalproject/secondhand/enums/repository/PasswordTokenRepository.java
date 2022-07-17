package com.finalproject.secondhand.enums.repository;

import com.finalproject.secondhand.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
}
