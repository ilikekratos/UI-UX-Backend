package com.example.parking.controller;

import com.example.parking.config.JwtConfig;
import com.example.parking.converters.UserConverter;
import com.example.parking.dtos.LoginDTO;
import com.example.parking.dtos.LoginResponseDTO;
import com.example.parking.dtos.UserDTO;
import com.example.parking.models.User;
import com.example.parking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

@RestController
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO){
        logger.trace("register - entered");
        var user= userConverter.convertDtoToModel(userDTO);
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        if(userService.checkUsername(user.getUsername())){
            logger.trace("register - failed");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username Exists");
        }
        userService.addUser(user.getUsername(),user.getPassword(),user.isAdmin());
        logger.trace("register - successful");
        return ResponseEntity.status(HttpStatus.CREATED).body("Good");
    }
    @GetMapping(value="/user/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){
        logger.trace("login - entered");
        if(userService.checkLogin(loginDTO.getUsername(),loginDTO.getPassword())){
            Optional<User> optionalUser=userService.returnUser(loginDTO.getUsername());
            if(optionalUser.isPresent()){
                UserDTO user=userConverter.convertModelToDto(optionalUser.get());
                String token=JwtConfig.generateToken(user);
                LoginResponseDTO loginResponseDTO= new LoginResponseDTO(token,user.isAdmin());
                logger.trace("login - passed");
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponseDTO);
            }
        }
        logger.trace("login - failed");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
