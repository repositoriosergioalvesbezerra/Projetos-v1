package com.processos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Blob;
import java.util.Date;

@Data
@Entity
@Table(name = "PROCESSO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Processo {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "npu")
    private String npu;

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Column(name = "data_visualizacao")
    private Date dataVisualizacao;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "uf")
    private String uf;


    @Column(name = "pdf")
    private Blob pdf;

    @Column(name = "visibilidade")
    private String visibilidade;


}
