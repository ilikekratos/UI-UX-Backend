package com.example.parking.converters;

import com.example.parking.dtos.UserDTO;
import com.example.parking.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConvertor<Long, User, UserDTO>{
    @Override
    public User convertDtoToModel(UserDTO userDTO){
        var model = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.isAdmin(), userDTO.getCompany_id());
        model.setCompany_id(userDTO.getCompany_id());
        return model;
    }

    @Override
    public UserDTO convertModelToDto(User user) {
        var userDTO= new UserDTO(user.getUsername(), user.getPassword(), user.isAdmin(), user.getCompany_id());
        userDTO.setId(user.getId());
        return userDTO;
    }
}
