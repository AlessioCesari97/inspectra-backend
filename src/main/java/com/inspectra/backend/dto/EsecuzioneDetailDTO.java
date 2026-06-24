package com.inspectra.backend.dto;


import lombok.Data;


@Data
public class EsecuzioneDetailDTO {


    private Long esecuzioneId;


    private Integer numero;


    private String stato;


    private String note;


    private String puntoPrevisto;



    // GPS

    private Double latitudine;


    private Double longitudine;



    // tempo

    private String timestamp;



    // foto piano

    private String fotoPiano1;


    private String fotoPiano2;


    private String fotoPiano3;



    // foto cantiere

    private String fotoCantiere1;


    private String fotoCantiere2;



    // prova

    private Long provaId;


    private String nomeProva;


    private String sigla;



    // piano

    private Long pianoId;


    private String codicePiano;



    // asset

    private Long assetId;


    private String nomeViadotto;



    // elemento

    private Long elementoId;


    private String codiceElemento;


    private String tipoElemento;


    private String campata;



    // ispezione

    private Long ispezioneId;


    private String titoloIspezione;



    // spostamento

    private Long spostatoElementoId;


    private String spostatoElementoCodice;


    private String spostataSuCampata;

}