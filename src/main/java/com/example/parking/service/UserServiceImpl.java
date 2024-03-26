package com.example.parking.service;

import com.example.parking.models.User;
import com.example.parking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(String username,String password,Boolean admin){
        var newUser=new User(username,password,admin);
        userRepository.save(newUser);
    }
    @Override
    public boolean checkUsername(String username){
        return userRepository.existsUserByUsername(username);
    }
}
