package com.spring3.oauth.jwt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author codingkrishna
 * @project oauth-jwt
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDTO {
    private String token;
}
