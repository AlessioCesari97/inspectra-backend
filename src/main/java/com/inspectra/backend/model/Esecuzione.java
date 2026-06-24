package com.inspectra.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import lombok.Data;


import java.time.LocalDateTime;


@Entity
@Data
public class Esecuzione {


    // =========================
    // ID
    // =========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long esecuzioneId;



    // =========================
    // DATI PRINCIPALI
    // =========================


    // numero progressivo della prova
    @NotNull
    private Integer numero;



    // campata dove viene svolta
    private String campata;



    // stato operativo
    @Enumerated(EnumType.STRING)
    private StatoEsecuzione stato =
            StatoEsecuzione.PREVISTA;



    @Column(length = 1000)
    private String note;



    // punto previsto dal piano
    private String puntoPrevisto;



    // =========================
    // GPS
    // =========================

    private Double latitudine;


    private Double longitudine;



    // =========================
    // TEMPO
    // =========================

    private LocalDateTime timestamp;




    // =========================
    // FOTO PIANO
    // =========================

    private String fotoPiano1;


    private String fotoPiano2;


    private String fotoPiano3;




    // =========================
    // FOTO CANTIERE
    // =========================


    private String fotoCantiere1;


    private String fotoCantiere2;




    // =========================
    // RELAZIONI
    // =========================



    // Ispezione dove viene realmente eseguita
    @ManyToOne
    @JoinColumn(name = "ispezione_id")
    @JsonIgnoreProperties("esecuzioni")
    private Ispezione ispezione;




    // Piano di appartenenza
    @ManyToOne
    @JoinColumn(name = "piano_id")
    @JsonIgnoreProperties("esecuzioni")
    private PianoIndagine pianoIndagine;




    // Tipo prova
    @ManyToOne
    @JoinColumn(name = "prova_id")
    private Prova prova;




    // elemento previsto
    @ManyToOne
    @JoinColumn(name = "elemento_id")
    private Elemento elemento;




    // elemento dove viene spostata
    @ManyToOne
    @JoinColumn(name = "spostato_elemento_id")
    private Elemento spostatoElemento;




    private String spostataSuCampata;

}