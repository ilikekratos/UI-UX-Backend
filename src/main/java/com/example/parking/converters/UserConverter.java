package com.example.parking.converters;

import com.example.parking.dtos.UserDTO;
import com.example.parking.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConvertor<Long, User, UserDTO>{
    @Override
    public User convertDtoToModel(UserDTO userDTO){
        return new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.isAdmin());
    }

    @Override
    public UserDTO convertModelToDto(User user) {
        var userDTO= new UserDTO(user.getUsername(), user.getPassword(), user.isAdmin());
        userDTO.setId(user.getId());
        return userDTO;
    }
}
