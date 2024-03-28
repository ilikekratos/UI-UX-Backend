package com.example.parking.service;

import com.example.parking.models.User;

import java.util.Optional;

public interface UserService {
    void addUser(String username,String password,Boolean admin);
    boolean checkUsername(String username);
    boolean checkLogin(String username,String password);
    Optional<User> returnUser(String username);
}
