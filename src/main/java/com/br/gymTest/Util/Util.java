package com.br.gymTest.Util;

import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.Usuario.service.implementation.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class Util {

    @Autowired
    private static UserService userService;

    public static User convertToUser (UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCpf(userDTO.getCpf());
        user.setPhone(userDTO.getPhone());
        user.setLastModifiedDate(new Date());
        return user;
    }

    public static UserDTO convertToUserDTO (User user){
        return new UserDTO(user);
    }

    public static Date convertDate(Date date) {
        return date;
        //TODO
    }

    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }
}
