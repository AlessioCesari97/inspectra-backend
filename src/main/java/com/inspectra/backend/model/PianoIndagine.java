package com.inspectra.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "piano_indagini")
@Data
public class PianoIndagine {


    // =========================
    // RELAZIONI
    // =========================

    // Un piano indagine può avere più esecuzioni
    @OneToMany(mappedBy = "pianoIndagine")
    @JsonIgnoreProperties("pianoIndagine")
    private List<Esecuzione> esecuzioni;


    // Un piano indagine può avere più ispezioni
    @OneToMany(mappedBy = "pianoIndagine")
    @JsonIgnoreProperties("pianoIndagine")
    private List<Ispezione> ispezioni;


    // Ogni piano appartiene ad un asset/viadotto
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;


    // =========================
    // ID
    // =========================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pianoId;


    // =========================
    // DATI PRINCIPALI
    // =========================

    @NotBlank(message = "Il codice piano è obbligatorio")
    private String codicePiano;

    private String revisione;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatoPiano stato;


    @Column(length = 1000)
    private String descrizione;

    @Column(length =1000)
    private String allegato;


    // =========================
    // FIRME / VALIDAZIONI
    // =========================

    private String redatto;

    private String verificato;

    private String approvato;
}
