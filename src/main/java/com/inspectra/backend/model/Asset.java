package com.inspectra.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Asset {

    /*
     * Lista degli elementi strutturali
     * associati all'opera.
     *
     * mappedBy = "asset"
     * indica che la relazione è gestita
     * dalla entity Elemento.
     */
    @OneToMany(mappedBy = "asset")
    @JsonIgnoreProperties("asset")
    private List<Elemento> elementi;


    /*
     * Lista dei piani di indagine
     * collegati all'opera.
     */
    @OneToMany(mappedBy = "asset")
    @JsonIgnoreProperties("asset")
    private List<PianoIndagine> piani;


    /*
     * Lista delle ispezioni
     * associate all'opera.
     */
    @OneToMany(mappedBy = "asset")
    @JsonIgnoreProperties("asset")
    private List<Ispezione> ispezioni;


    /*
     * Identificativo univoco dell'opera.
     *
     * Viene generato automaticamente
     * dal database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetId;


    /*
     * Nome del viadotto/opera.
     *
     * Campo obbligatorio.
     */
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;


    /*
     * Strada o tratta stradale
     * a cui appartiene l'opera.
     *
     * Campo obbligatorio.  
     */
    @NotBlank(message = "La strada è obbligatoria")
    private String strada;


    /*
     * Ente gestore dell'opera.
     *
     * Campo obbligatorio.
     */
    @NotBlank(message = "Il gestore è obbligatorio")
    private String gestore;


    /*
     * Posizione dell'opera.
     *
     * Questo campo viene utilizzato
     * per salvare direttamente una posizione
     * copiata da Google Maps oppure
     * una descrizione operativa.
     *
     * Campo obbligatorio.
     */
    @NotBlank(message = "La posizione è obbligatoria")
    private String posizione;


    /*
     * Note aggiuntive opzionali.
     *
     * Il campo può essere lasciato vuoto.
     */
    @Column(length = 2000)
    private String note;
}