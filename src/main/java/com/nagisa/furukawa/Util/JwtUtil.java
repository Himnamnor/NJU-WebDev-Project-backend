package com.nagisa.furukawa.Util;

import com.nagisa.furukawa.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {

    @Autowired
    UserService userService;

    @Value("${spring.jwt.secret}")
    private String secret;

    private final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String username){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims, username);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    private Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String token){
        String extractedUsername = extractUsername(token);
        boolean usernameExist=userService.containUsername(extractedUsername);
        return usernameExist&&!isTokenExpired(token);
    }

}