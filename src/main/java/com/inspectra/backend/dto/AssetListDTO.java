package com.inspectra.backend.dto;

import lombok.Data;

@Data
public class AssetListDTO {

    private Long  assetId;

    private String nome;

    private String strada;

    private String gestore;

    private Integer numeroPiani;


    private Integer numeroElementi;


    private Integer numeroIspezioni;
}