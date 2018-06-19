package pl.myprivatepocket.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.prefix}")
    private String jwtPrefix;

    @Value("${jwt.header}")
    private String jwtHeader;

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("created", this.generateCurrentDate());

        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public String getSubjectFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token.replace(jwtPrefix, ""))
                .getBody()
                .getSubject();
    }

    public Long getExpiration() {
        return expiration;
    }

    public String getJwtPrefix() {
        return jwtPrefix;
    }

    public String getJwtHeader() {
        return jwtHeader;
    }
}
