package com.br.gymTest.Usuario.service.implementation;

import com.br.gymTest.Usuario.mapper.UserMapper;
import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.Usuario.repository.UserRepository;
import com.br.gymTest.Usuario.service.UserServiceInterface;
import com.br.gymTest.Util.Util;
import com.br.gymTest.exceptions.LogLevel;
import com.br.gymTest.Usuario.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = repository.findAll();
        return userMapper.toDto(users);
    }

    @Override
    public UserDTO findUserById(UUID id) {
        User user = (repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND, LogLevel.ERROR)));
        return new UserDTO(user);
    }

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(repository.save(user));
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND, LogLevel.ERROR));
        user = userMapper.toEntity(userDTO);
        user.setId(id);
        user = repository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(UUID id) {
        findUserById(id);
        repository.deleteById(id);
    }
}
