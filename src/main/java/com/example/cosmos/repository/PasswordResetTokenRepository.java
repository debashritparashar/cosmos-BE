package com.example.cosmos.repository;

import com.example.cosmos.entity.PasswordResetToken;
import com.example.cosmos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByEmailAndPhoneNumber(
            String email,
            String phoneNumber
    );
}
