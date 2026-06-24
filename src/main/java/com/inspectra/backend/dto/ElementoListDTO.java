package com.inspectra.backend.dto;

import lombok.Data;

@Data
public class ElementoListDTO {

    private Long elementoId;

    private String codice;

    private Long tipoElementoId;

    private String tipoElementoNome;

    private String campata;

    private String lato;
}