package com.inspectra.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class PianoIndagineDetailDTO {

    private Long pianoId;

    private String codicePiano;

    private String revisione;

    private String data;

    private String descrizione;

    private String redatto;

    private String verificato;

    private String approvato;

    private String allegato;

    private String stato;

    // asset collegato
    private String assetNome;
    private Long assetId;

    // ispezioni collegate
    private List<IspezioneListDTO> ispezioni;

    //esecuzioni collegate
    private List<EsecuzioneListDTO> esecuzioni;
}