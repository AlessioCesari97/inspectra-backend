package com.inspectra.backend.dto;

import lombok.Data;

@Data
public class ProvaDetailDTO {

    private Long provaId;

    private String nomeProva;

    private String sigla;

    private String descrizione;

    private String noteGenerali;
}
