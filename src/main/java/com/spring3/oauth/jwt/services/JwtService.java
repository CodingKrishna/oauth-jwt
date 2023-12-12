package com.spring3.oauth.jwt.services;

import com.spring3.oauth.jwt.exceptions.AuthAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author codingkrishna
 * @project oauth-jwt
 */

@Component
public class JwtService {

    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        boolean isValidFormat = validateTokenFormat(token);
        boolean isNotExpired = !isTokenExpired(token);
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && isNotExpired);
    }

    public String GenerateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }



    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*1))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // validate Jwt token
    public boolean validateTokenFormat(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new AuthAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new AuthAPIException(HttpStatus.UNAUTHORIZED, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new AuthAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new AuthAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }
}
