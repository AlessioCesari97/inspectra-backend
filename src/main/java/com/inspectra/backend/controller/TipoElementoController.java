package com.inspectra.backend.controller;


import com.inspectra.backend.model.TipoElemento;


import com.inspectra.backend.repository.TipoElementoRepository;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;



import java.util.List;





@RestController
@RequestMapping("/tipi-elemento")
@CrossOrigin("*")
public class TipoElementoController {




    private final TipoElementoRepository repository;





    public TipoElementoController(

            TipoElementoRepository repository

    ) {


        this.repository = repository;


    }









    // =========================
    // LISTA TIPI ELEMENTO
    // =========================


    @GetMapping
    public List<TipoElemento> getAll() {


        return repository.findAll();


    }









    // =========================
    // CREA TIPO ELEMENTO
    // =========================


    @PostMapping
    public ResponseEntity<?> create(



            @RequestBody TipoElemento tipo


    ) {






        if(

                tipo.getNome() == null

                        ||

                        tipo.getNome().trim().isEmpty()

        ){



            return ResponseEntity

                    .badRequest()

                    .body(

                            "Il nome del tipo elemento è obbligatorio"

                    );


        }









        tipo.setNome(


                tipo.getNome()

                        .trim()

                        .toUpperCase()


        );









        boolean exists =


                repository.existsByNomeIgnoreCase(


                        tipo.getNome()


                );








        if(exists){



            return ResponseEntity


                    .status(HttpStatus.CONFLICT)


                    .body(

                            "Tipo elemento già presente"

                    );



        }










        TipoElemento saved =


                repository.save(tipo);








        return ResponseEntity.ok(


                saved


        );



    }


}