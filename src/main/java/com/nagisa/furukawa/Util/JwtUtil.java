package com.nagisa.furukawa.Util;

import com.nagisa.furukawa.PO.User;
import com.nagisa.furukawa.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserRepository userRepository;

    @Setter
    private String secret;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(Integer userId){
        String userIdString=userId.toString();
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims, userIdString);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Integer extractUserId(String token){
        String userIdString= extractAllClaims(token).getSubject();
        try{
            Integer userId=Integer.parseInt(userIdString);
            return userId;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String token){
        Integer userId = extractUserId(token);
        User user=userRepository.findByUserId(userId);
        return user!=null&&!isTokenExpired(token);
    }



}