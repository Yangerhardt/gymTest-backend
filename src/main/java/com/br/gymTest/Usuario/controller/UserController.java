package com.br.gymTest.Usuario.controller;

import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.Usuario.service.implementation.UserService;
import com.br.gymTest.exceptions.UserNotFoundException;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiResponse(message = "Return all users", code = 200)
    @GetMapping
    public ResponseEntity<?> getUsers () {
        if (userService.findAllUsers().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userService.findAllUsers());
    }

    @ApiResponse(message = "Returns a user by its ID", code = 200)
    @GetMapping("/id")
    public ResponseEntity<?> getUserById (@RequestParam UUID id) {
        try {
            UserDTO userDTO = userService.findUserById(id);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException e) { //TODO Faltou colocar exceptions específicas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiResponse(message = "Creates a new user", code = 201)
    @PostMapping
    public ResponseEntity<?> createUser (@Valid @RequestBody User user) {
        try {
            User newUser = userService.createNewUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ApiResponse(message = "Alters a user", code = 200)
    @PutMapping
    public ResponseEntity<?> updateUser (@Valid @PathVariable UUID id, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUserDTO = userService.updateUser(id, userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUserDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ApiResponse(message = "Removes a user", code = 200)
    @DeleteMapping("/id")
    public ResponseEntity<?> deleteUser (@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
