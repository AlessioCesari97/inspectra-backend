package com.inspectra.backend.dto;

import lombok.Data;


@Data
public class IspezioneListDTO {


    private Long ispezioneId;


    private String titoloIspezione;


    private String dataIspezione;


    private String stato;


    private String assetNome;


    private Integer numeroProve;


    // PIANO INDAGINE

    private Long pianoId;


    private String codicePiano;


}