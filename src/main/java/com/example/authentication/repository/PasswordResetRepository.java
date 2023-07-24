package com.example.authentication.repository;

import com.example.authentication.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken,Long> {
    public PasswordResetToken findByToken(String token);
}
