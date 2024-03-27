package com.example.parking.config;

import com.example.parking.dtos.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class JwtConfig {
    private static final long EXPIRATION_TIME = 1000*60*60;
    private static String SECRET;
    @Value("${jwt.secret}")
    public void setSECRET(String name){
        JwtConfig.SECRET=name;
    }

    public static String generateToken(UserDTO userDTO) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        Map<String,Object> claims= new HashMap<>();
        claims.put("admin",String.valueOf(userDTO.isAdmin()));
        return Jwts.builder().
                subject(userDTO.getUsername()).
                claims(claims).
                issuedAt(now).
                expiration(expiryDate).
                signWith(SignatureAlgorithm.HS512,SECRET).compact();
    }
}
