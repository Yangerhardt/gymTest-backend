package com.br.gymTest.audit.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class DefaultAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @ApiModelProperty(hidden = true)
    @Column(name = "created_by")
    private final String createdBy = "admin";

    @CreatedDate
    @ApiModelProperty(hidden = true)
    @Column(name = "created_date", updatable = false, nullable = false)
    private final Date createdDate = new Date();

    @LastModifiedDate
    @ApiModelProperty(hidden = true)
    @Column(name = "last_modified_date", updatable = false, nullable = false)
    private Date lastModifiedDate = new Date();

    @LastModifiedBy
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @Column(name = "last_modified_by")
    private String lastModifiedBy ="admin";

    @NotNull
//    @ApiModelProperty(hidden = true)
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public abstract UUID getId();
}
