package com.inspectra.backend.dto;

import lombok.Data;

import java.util.List;


@Data
public class IspezioneDetailDTO {


    private Long ispezioneId;


    private String titoloIspezione;


    private String dataIspezione;


    private String stato;



    private String operatoreProve;


    private String ingegnere;


    private String referenteConcessionaria;



    private String installazioneCantiere;


    private String inizioLavori;


    private String fineLavori;



    private String firmaOperatore;


    private String firmaIngegnere;


    private String firmaConcessionaria;



    private String createdBy;


    private String annotazioniAggiuntive;


    private String report;



    // ASSET

    private Long assetId;


    private String assetNome;



    // PIANO INDAGINE

    private Long pianoId;


    private String codicePiano;



    // ESECUZIONI

    private List<EsecuzioneListDTO> esecuzioni;


}