package com.inspectra.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TipoElemento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoElementoId;

    private String nome;
}