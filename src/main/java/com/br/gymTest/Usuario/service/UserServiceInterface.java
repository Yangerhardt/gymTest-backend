package com.br.gymTest.Usuario.service;

import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public interface UserServiceInterface {

    List<UserDTO> findAllUsers();
    UserDTO findUserById(UUID id);
    UserDTO createNewUser(UserDTO userDTO);
    UserDTO updateUser(UUID ID, UserDTO userDTO);
    void deleteUser(UUID id);
}
