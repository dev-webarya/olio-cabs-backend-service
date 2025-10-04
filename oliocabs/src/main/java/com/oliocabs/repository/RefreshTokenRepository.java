package com.oliocabs.repository;

import com.oliocabs.entity.User; // MODIFIED
import com.oliocabs.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(User user); // MODIFIED: Was UserInfo
}