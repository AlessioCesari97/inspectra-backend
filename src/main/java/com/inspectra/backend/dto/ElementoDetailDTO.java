package com.inspectra.backend.dto;


import lombok.Data;

import java.util.List;



@Data
public class ElementoDetailDTO {


    private Long elementoId;


    private String codice;


    private Long tipoElementoId;


    private String tipoElementoNome;


    private String campata;


    private String lato;


    private String descrizione;


    private String foto;


    private Double latitudine;


    private Double longitudine;



    // OPERA COLLEGATA

    private Long assetId;


    private String nome;



    private List<EsecuzioneListDTO> esecuzioni;


}