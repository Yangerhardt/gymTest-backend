package com.br.gymTest.Usuario.service.implementation;

import com.br.gymTest.Usuario.exception.MissingParametersException;
import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.Usuario.repository.UserRepository;
import com.br.gymTest.Usuario.service.UserServiceInterface;
import com.br.gymTest.Util.Util;
import com.br.gymTest.exceptions.LogLevel;
import com.br.gymTest.Usuario.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository repository;

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> all = repository.findAll("users");
        List<UserDTO> allDTO = new ArrayList<UserDTO>();

        for (User user : all) {
            allDTO.add(Util.convertToUserDTO(user));
        }

        return allDTO;
    }

    @Override
    public UserDTO findUserById(UUID id) {
        User user = (repository.findById("users", id)
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND, LogLevel.ERROR)));
        return new UserDTO(user);
    }

    @Override
    public User createNewUser(User user) {
        return repository.save(user);
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND, LogLevel.ERROR));
        userDTO.setId(id);
        user = Util.convertToUser(userDTO);

        repository.save(user);

        return userDTO = Util.convertToUserDTO(user);
    }

    @Override
    public void deleteUser(UUID id) {
        findUserById(id);
        repository.deleteById("users", id);
    }
}
