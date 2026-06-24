package com.inspectra.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;


import lombok.Data;


import java.util.List;





@Entity
@Data
public class Elemento {


    // =========================
    // ID
    // =========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long elementoId;







    // =========================
    // DATI PRINCIPALI
    // =========================


    @NotBlank(message = "Il codice è obbligatorio")
    private String codice;





    @NotNull(message = "Il tipo elemento è obbligatorio")
    @ManyToOne
    @JoinColumn(name = "tipo_elemento_id")
    private TipoElemento tipo;





    @NotBlank(message = "La campata è obbligatoria")
    private String campata;





    @NotNull(message = "Il lato è obbligatorio")
    @Enumerated(EnumType.STRING)
    private Lato lato;







    @Column(length = 1000)
    private String descrizione;





    private String foto;





    private Double latitudine;


    private Double longitudine;









    // =========================
    // RELAZIONI
    // =========================


    @NotNull(message = "Il viadotto è obbligatorio")
    @ManyToOne
    @JoinColumn(name = "asset_id")
    @JsonIgnoreProperties("elementi")
    private Asset asset;







    @OneToMany(mappedBy = "elemento")
    @JsonIgnoreProperties("elemento")
    private List<Esecuzione> esecuzioni;



}