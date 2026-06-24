
package com.inspectra.backend.dto;


import lombok.Data;



@Data
public class PianoIndagineListDTO {



    private Long pianoId;


    private String codicePiano;


    private String revisione;


    private String data;


    private String stato;


    private Long assetId;


    private String assetNome;


    private int numeroEsecuzioni;

    private int numeroIspezioni;



}