package com.br.gymTest.Usuario.repository;

import com.br.gymTest.Usuario.model.User;
import com.br.gymTest.audit.repository.JpaAuditableRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaAuditableRepository<User, UUID> {
}
