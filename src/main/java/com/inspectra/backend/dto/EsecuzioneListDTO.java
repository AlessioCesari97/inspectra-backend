package com.inspectra.backend.dto;


import lombok.Data;


@Data
public class EsecuzioneListDTO {


    private Long esecuzioneId;


    private Integer numero;


    private String stato;


    // prova

    private Long provaId;

    private String nomeProva;

    private String sigla;


    // ELEMENTO

    private Long elementoId;


    private String codiceElemento;


    // posizione

    private String campata;


    // piano

    private String codicePiano;


    // ispezione

    private String titoloIspezione;

}