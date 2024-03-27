package com.example.parking.controller;

import com.example.parking.config.JwtConfig;
import com.example.parking.converters.UserConverter;
import com.example.parking.dtos.LoginDTO;
import com.example.parking.dtos.UserDTO;
import com.example.parking.models.User;
import com.example.parking.service.UserService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;
@RestController
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final JwtConfig jwtConfig;
    @Autowired
    private UserService userService;
    @Autowired
    UserConverter userConverter;
    @Autowired
    public UserController(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO){
        logger.trace("addUser - entered");
        var user= userConverter.convertDtoToModel(userDTO);
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        if(userService.checkUsername(user.getUsername())){
            logger.trace("addUser - failed");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username Exists");
        }
        userService.addUser(user.getUsername(),user.getPassword(),user.isAdmin());
        logger.trace("addUser - successful");
        return ResponseEntity.status(HttpStatus.CREATED).body("Good");
    }
    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserDTO loginDTO){
        logger.trace("login - entered");
        String mystring1=JwtConfig.generateToken(loginDTO);
        logger.info("Passed");
        String responseBody = String.format("mystring1: %s", mystring1);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }
}
