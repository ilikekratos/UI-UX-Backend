package com.example.parking.config;

import com.example.parking.dtos.UserDTO;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

import static io.jsonwebtoken.Jwts.SIG.HS512;


@Component
public class JwtConfig {
    public static final Logger logger = LoggerFactory.getLogger(JwtConfig.class);
    private static final long EXPIRATION_TIME = 1000*60*60;
    private static final Key secret= HS512.key().build();
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
                signWith(secret).compact();
    }
    public static boolean verifyToken(String token){
        JwtParser jwtParser= Jwts.parser().verifyWith((SecretKey) secret).build();
        try{
            jwtParser.parse(token);
            return true;
        }
        catch (Exception e){
            logger.info(String.valueOf(e));
        return false;}
    }
}
//private static String SECRET;

//    @Value("${jwt.secret}")
//    public void setSECRET(String name){
//        JwtConfig.SECRET=name;
//    }
