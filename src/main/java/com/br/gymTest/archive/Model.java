package com.br.gymTest.archive;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {

    @ApiModelProperty(hidden = true)
    @Column(name = "createdBy")
    private final String createdBy = "admin";

    @ApiModelProperty(hidden = true)
    @Column(name = "createdDate", updatable = false, nullable = false)
    private final Date createdDate = new Date();

    @ApiModelProperty(hidden = true)
    @Column(name = "lastModifiedDate", updatable = false, nullable = false)
    private Date lastModifiedDate = new Date();

    @ApiModelProperty(hidden = true)
    @Column(name = "lastModifiedBy")
    private String lastModifiedBy ="admin";

    public String teste() {
        return "teste";
    }
}
