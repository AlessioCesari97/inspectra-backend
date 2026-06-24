package com.inspectra.backend.controller;


import com.inspectra.backend.dto.ElementoDetailDTO;
import com.inspectra.backend.dto.ElementoListDTO;

import com.inspectra.backend.model.Elemento;

import com.inspectra.backend.repository.ElementoRepository;

import com.inspectra.backend.service.ElementoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;





@RestController
@RequestMapping("/elementi")
@CrossOrigin("*")
public class ElementoController {



    private final ElementoService service;


    private final ElementoRepository repository;




    public ElementoController(

            ElementoService service,

            ElementoRepository repository

    ){


        this.service = service;


        this.repository = repository;


    }








    // =========================
    // LISTA
    // =========================


    @GetMapping
    public List<ElementoListDTO> getAll(){


        return service.getAllElementi();


    }








    // =========================
    // DETTAGLIO
    // =========================


    @GetMapping("/{elementoId}")
    public ElementoDetailDTO getDetail(

            @PathVariable Long elementoId

    ){


        return service.getElementoDetail(

                elementoId

        );


    }











    // =========================
    // CREATE
    // =========================


    @PostMapping
    public ResponseEntity<?> create(


            @Valid

            @RequestBody Elemento elemento

    ){



        elemento.setCodice(

                elemento.getCodice()
                        .trim()
                        .toUpperCase()

        );



        elemento.setCampata(

                elemento.getCampata()
                        .trim()
                        .toUpperCase()

        );






        boolean exists =


                repository

                        .existsByAssetAssetIdAndCodiceIgnoreCase(


                                elemento
                                        .getAsset()
                                        .getAssetId(),


                                elemento.getCodice()

                        );





        if(exists){


            return ResponseEntity

                    .status(HttpStatus.CONFLICT)

                    .body(

                            "Elemento già presente nel viadotto"

                    );


        }







        Elemento saved =

                repository.save(elemento);





        return ResponseEntity.ok(

                service.toDetailDTO(saved)

        );


    }











    // =========================
    // UPDATE
    // =========================


    @PutMapping("/{elementoId}")
    public ResponseEntity<?> update(



            @PathVariable Long elementoId,


            @Valid

            @RequestBody Elemento updatedElemento


    ){






        Elemento elemento =


                repository.findById(elementoId)


                        .orElse(null);






        if(elemento == null){



            return ResponseEntity

                    .status(HttpStatus.NOT_FOUND)

                    .body(

                            "Elemento non trovato"

                    );


        }







        elemento.setCodice(


                updatedElemento.getCodice()

                        .trim()

                        .toUpperCase()


        );





        elemento.setCampata(


                updatedElemento.getCampata()

                        .trim()

                        .toUpperCase()


        );






        elemento.setTipo(

                updatedElemento.getTipo()

        );




        elemento.setLato(

                updatedElemento.getLato()

        );





        elemento.setDescrizione(

                updatedElemento.getDescrizione()

        );





        elemento.setFoto(

                updatedElemento.getFoto()

        );





        elemento.setLatitudine(

                updatedElemento.getLatitudine()

        );





        elemento.setLongitudine(

                updatedElemento.getLongitudine()

        );







        Elemento saved =


                repository.save(elemento);







        return ResponseEntity.ok(

                service.toDetailDTO(saved)

        );


    }









    // =========================
    // DELETE
    // =========================


    @DeleteMapping("/{elementoId}")
    public ResponseEntity<?> delete(

            @PathVariable Long elementoId

    ){



        if(!repository.existsById(elementoId)){


            return ResponseEntity

                    .status(HttpStatus.NOT_FOUND)

                    .body(

                            "Elemento non trovato"

                    );


        }





        repository.deleteById(elementoId);





        return ResponseEntity.ok(

                "Elemento eliminato"

        );


    }



}