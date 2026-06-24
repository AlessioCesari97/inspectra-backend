package com.inspectra.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Data
public class Ispezione {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ispezioneId;


    // =========================
    // DATI BASE
    // =========================


    @NotBlank(
            message = "Il titolo ispezione è obbligatorio"
    )
    private String titoloIspezione;


    @NotNull(
            message = "La data ispezione è obbligatoria"
    )
    private LocalDate dataIspezione;



    @Enumerated(EnumType.STRING)
    private StatoIspezione stato =
            StatoIspezione.BOZZA;



    // =========================
    // BOZZA
    // =========================


    private String operatoreProve;


    private String ingegnere;


    private String referenteConcessionaria;


    @NotBlank(
            message = "Creatore obbligatorio"
    )
    private String createdBy;



    // =========================
    // IN CORSO
    // =========================


    private LocalTime installazioneCantiere;


    private LocalTime inizioLavori;



    // =========================
    // COMPLETATA
    // =========================


    private LocalTime fineLavori;



    // =========================
    // FIRME
    // =========================


    private String firmaOperatore;


    private String firmaIngegnere;


    private String firmaConcessionaria;



    // =========================
    // EXTRA
    // =========================


    @Column(length = 1000)
    private String annotazioniAggiuntive;


    private String report;



    // =========================
    // RELAZIONI
    // =========================


    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id")
    private Asset asset;



    @ManyToOne
    @JoinColumn(name = "piano_id")
    private PianoIndagine pianoIndagine;



    @OneToMany(mappedBy = "ispezione")
    @JsonIgnoreProperties("ispezione")
    private List<Esecuzione> esecuzioni;


}