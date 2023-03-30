package com.br.gymTest.Usuario.mapper;

import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCpf(),
                user.getPhone()
        );
    }

    public List<UserDTO> toDto(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User(userDTO);
        user.setId(userDTO.getId());
        return user;
    }
}
