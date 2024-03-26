package com.example.parking.controller;

import com.example.parking.converters.UserConverter;
import com.example.parking.dtos.UserDTO;
import com.example.parking.models.User;
import com.example.parking.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    final
    UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO){
        var user= userConverter.convertDtoToModel(userDTO);
        if(userService.checkUsername(user.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username Exists");
        }
        userService.addUser(user.getUsername(),user.getPassword(),user.isAdmin());
        return ResponseEntity.status(HttpStatus.CREATED).body("Good");
    }
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    //public ResponseEntity<String> login(@RequestBody )
}
