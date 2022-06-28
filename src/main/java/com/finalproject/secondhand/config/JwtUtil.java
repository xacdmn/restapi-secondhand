package com.finalproject.secondhand.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private Key secretKey;

    public JwtUtil() {
        generateSecret();
    }

    // Generate random key
    public void generateSecret() {
        logger.info("Generating Key");
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    // generate token
    public String generateAccessToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info(String.valueOf(userDetails));
        Map<String, Object> claims = new HashMap<>();
        userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                .forEach(claim -> claims.put("roles", claim));
        int jwtExpirationInMs = 18000000; // -> Refres 5 hours
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(secretKey)
                .addClaims(claims)
                .compact();
    }

    public Optional<String> parseJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);
        logger.info(authHeader);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return Optional.of(authHeader.substring("Bearer ".length()));
        }
        return Optional.empty();
    }

    public Optional<Claims> validateJwtToken(String authToken) {
        try {
            return Optional.of(Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken).getBody());
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return Optional.empty();
    }

}
