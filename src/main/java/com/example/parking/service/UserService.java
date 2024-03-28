package com.example.parking.service;

import com.example.parking.models.User;

public interface UserService {
    void addUser(String username,String password,Boolean admin);
    boolean checkUsername(String username);
    boolean checkLogin(String username,String password);
}
