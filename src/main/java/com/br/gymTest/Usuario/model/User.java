package com.br.gymTest.Usuario.model;

import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.archive.Model;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@ApiModel
public class User extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", nullable = false, length = 55)
    private String name;
    @Column(name = "email", nullable = false, length = 55)
    private String email;
    @Column(name = "password", nullable = false, length = 55)
    private String password;
    @Column(name = "cpf", nullable = false, length = 55, updatable = false)
    private String cpf;
    @Column(name = "phone", nullable = false, length = 55)
    private String phone;

    @Override
    public String teste() {
        return "Teste de herança";
    }

    public User(UserDTO userDTO) {
        this.id = UUID.randomUUID();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.cpf = userDTO.getCpf();
        this.phone = userDTO.getPhone();
    }
}
