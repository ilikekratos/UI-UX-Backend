package com.example.parking.service;

import com.example.parking.models.User;

public interface UserService {
    void addUser(String username,String password,Boolean admin,Long company_id);
}
