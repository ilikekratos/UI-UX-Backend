package com.example.parking.controller;

import com.example.parking.converters.UserConverter;
import com.example.parking.dtos.UserDTO;
import com.example.parking.models.User;
import com.example.parking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserConverter userConverter;
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    void addUser(@RequestBody UserDTO userDTO){
        var user= userConverter.convertDtoToModel(userDTO);
        userService.addUser(user.getUsername(),user.getPassword(),user.isAdmin(),user.getCompany_id());
    }

}
