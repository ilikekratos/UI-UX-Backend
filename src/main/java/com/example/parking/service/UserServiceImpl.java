package com.example.parking.service;

import com.example.parking.config.JwtConfig;
import com.example.parking.models.User;
import com.example.parking.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Override
    public boolean checkLogin(String username, String password) {
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            return BCrypt.checkpw(password,user.getPassword());
        }
        return false;
    }
}
