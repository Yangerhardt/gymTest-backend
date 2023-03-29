package com.br.gymTest.Usuario.model;

import com.br.gymTest.Usuario.model.dto.UserDTO;
import com.br.gymTest.audit.model.DefaultAuditingEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@ApiModel
public class User extends DefaultAuditingEntity {

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

    public User(UserDTO userDTO) {
        this.id = UUID.randomUUID();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.cpf = userDTO.getCpf();
        this.phone = userDTO.getPhone();
    }

}
