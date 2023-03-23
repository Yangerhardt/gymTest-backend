package com.br.gymTest.Usuario.controller;

import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.Usuario.service.implementation.UserService;
import com.br.gymTest.Util.Util;
import com.br.gymTest.exceptions.DefaultAbstractException;
import com.br.gymTest.Usuario.exception.UserNotFoundException;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final String USER_DOMAIN = "user";

    @Autowired
    private UserService userService;

    @ApiResponse(message = "Return all users", code = 200)
    @GetMapping
    public ResponseEntity<?> getUsers () {
        try {
            return ResponseEntity.ok(userService.findAllUsers());
        } catch (DefaultAbstractException ex) {
            Util.logger("findAllUsers", this.getClass(), USER_DOMAIN, ex);
            throw ex;
        } catch (Exception ex) {
            Util.logger("findAllUsers", this.getClass(), USER_DOMAIN, ex);
            throw ex;
        }
    }

    @ApiResponse(message = "Returns a user by its ID", code = 200)
    @GetMapping("/id")
    public ResponseEntity<?> getUserById (@RequestParam UUID id) {
        try {
            UserDTO userDTO = userService.findUserById(id);
            return ResponseEntity.ok(userDTO);
        } catch (DefaultAbstractException ex) {
            Util.logger("getUserById", this.getClass(), USER_DOMAIN, ex, Optional.of(id));
            throw ex;
        } catch (Exception ex) {
            Util.logger("getUserById", this.getClass(), USER_DOMAIN, ex);
            throw ex;
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
        } // TODO
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
        } // TODO
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
        } //TODO
    }
}
