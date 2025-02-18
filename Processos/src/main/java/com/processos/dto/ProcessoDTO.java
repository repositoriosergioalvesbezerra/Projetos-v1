package com.processos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.processos.constants.Constantes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import java.sql.Blob;
import java.util.Date;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessoDTO {

    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @NotEmpty(message = Constantes.INVALID_FIELDS)
    @NotBlank(message = Constantes.MISSING_FIELDS)
    @Pattern(regexp = "^[0-9]{7}[-][0-9]{2}[.][0-9]{4}[.][0-9]{1}[.][0-9]{2}[.][0-9]{4}?$", message = Constantes.NPU_NOT_VALID_EXCEPTION)
    @JsonProperty("npu")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String npu;

    @JsonProperty("data_cadastro")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dataCadastro;

    @JsonProperty("data_visualizacao")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date dataVisualizacao;

    @NotEmpty(message = Constantes.INVALID_FIELDS)
    @NotBlank(message = Constantes.MISSING_FIELDS)
    @JsonProperty("municipio")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String municipio;

    @NotEmpty(message = Constantes.INVALID_FIELDS)
    @NotBlank(message = Constantes.MISSING_FIELDS)
    @JsonProperty("uf")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uf;

    @JsonProperty("pdf")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Blob pdf;

    @JsonProperty("visibilidade")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String visibilidade;

}
