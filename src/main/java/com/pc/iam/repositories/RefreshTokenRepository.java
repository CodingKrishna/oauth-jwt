package com.pc.iam.repositories;

import com.pc.iam.helpers.RefreshableCRUDRepository;
import com.pc.iam.models.RefreshToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author codingkrishna
 * @project oauth-jwt
 */
@Repository
public interface RefreshTokenRepository extends RefreshableCRUDRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);
}
