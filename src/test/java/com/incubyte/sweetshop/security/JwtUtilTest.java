package com.incubyte.sweetshop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    @Test
    void jwtTokenShouldContainRoleClaim() {

        JwtUtil jwtUtil = new JwtUtil(
                "mysecretkeymysecretkeymysecretkey"
        );

        String token = jwtUtil.generateToken("admin", "USER");

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(
                        "mysecretkeymysecretkeymysecretkey".getBytes()
                ))
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertThat(claims.get("role")).isEqualTo("USER");
    }
}
