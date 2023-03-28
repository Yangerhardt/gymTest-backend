package com.br.gymTest.audit.repository;

import com.br.gymTest.audit.model.DefaultAuditingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAuditableRepository <T extends DefaultAuditingEntity, id extends  UUID> extends JpaRepository <T, id> {

    @Modifying
    @Query("SELECT * FROM #{entityName} t WHERE t.deleted=false AND t.id = :id")
    Optional<T> findById(@Param("entityName") String entityName, @Param("id") id id);

    @Modifying
    @Query("SELECT * FROM #{entityName} t WHERE t.deleted=false")
    List<T> findAll(@Param("entityName") String entityName);

    @Modifying
    @Query("UPDATE #{entityName} t SET t.deleted=true WHERE t.id = :id")
    void deleteById(@Param("entityName") String entityName, @Param("id") id id);

}
