package com.br.gymTest.Usuario.service.implementation;

import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.Usuario.repository.UserRepository;
import com.br.gymTest.Usuario.service.UserServiceInterface;
import com.br.gymTest.Util.Util;
import com.br.gymTest.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository repository;

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> all = repository.findAll();
        List<UserDTO> allDTO = new ArrayList<UserDTO>();

        for (User user : all) {
            allDTO.add(Util.convertToUserDTO(user));
        }

        return allDTO;
    }

    @Override
    public UserDTO findUserById(UUID id) {
        Optional<User> user = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found")));
        UserDTO userDTO = new UserDTO(user.get());
        return userDTO;
    }

    @Override
    public User createNewUser(User user) {
        return repository.save(user);
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userDTO.setId(id);
        user = Util.convertToUser(userDTO);

        repository.save(user);

        return userDTO = Util.convertToUserDTO(user);
    }

    @Override
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
