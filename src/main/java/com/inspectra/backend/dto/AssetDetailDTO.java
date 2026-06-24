package com.inspectra.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssetDetailDTO {

    private Long assetId;

    private String nome;

    private String strada;

    private String gestore;

    private String posizione;

    private String note;

    // lista piani,elementi,ispezioni collegate
    private List<PianoIndagineListDTO> piani;
    private List<ElementoListDTO> elementi;
    private List<IspezioneListDTO> ispezioni;
}