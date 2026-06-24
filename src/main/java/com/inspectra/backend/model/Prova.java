package com.inspectra.backend.model;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;


import lombok.Data;



@Entity
@Data
public class Prova {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long provaId;




    @NotBlank(
            message = "Il nome prova è obbligatorio"
    )
    private String nomeProva;





    @NotBlank(
            message = "La sigla è obbligatoria"
    )
    private String sigla;






    @Column(length = 1000)
    private String descrizione;





    @Column(length = 1000)
    private String noteGenerali;



}