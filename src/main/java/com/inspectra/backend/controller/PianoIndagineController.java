package com.inspectra.backend.controller;



import com.inspectra.backend.dto.PianoIndagineDetailDTO;
import com.inspectra.backend.dto.PianoIndagineListDTO;

import com.inspectra.backend.model.PianoIndagine;
import com.inspectra.backend.model.StatoPiano;

import com.inspectra.backend.repository.PianoIndagineRepository;

import com.inspectra.backend.service.PianoIndagineService;


import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.ResponseEntity;


import java.util.List;







@RestController
@RequestMapping("/piani-indagine")
@CrossOrigin("*")
public class PianoIndagineController {





    private final PianoIndagineService service;


    private final PianoIndagineRepository repository;







    public PianoIndagineController(


            PianoIndagineService service,


            PianoIndagineRepository repository


    ){


        this.service = service;


        this.repository = repository;


    }









    // =====================
    // LISTA
    // =====================


    @GetMapping
    public List<PianoIndagineListDTO> getAll(){



        return service.getAllPiani();



    }









    // =====================
    // DETTAGLIO
    // =====================


    @GetMapping("/{pianoId}")
    public PianoIndagineDetailDTO getDetail(


            @PathVariable Long pianoId


    ){



        return service.getPianoDetail(pianoId);



    }










    // =====================
    // CREAZIONE
    // =====================


    @PostMapping
    public PianoIndagine create(



            @Valid

            @RequestBody PianoIndagine piano



    ){





        piano.setCodicePiano(


                piano.getCodicePiano()
                        .trim()
                        .toUpperCase()


        );







        boolean exists =


                repository.existsByCodicePianoIgnoreCase(


                        piano.getCodicePiano()


                );








        if(exists){



            throw new ResponseStatusException(


                    HttpStatus.CONFLICT,


                    "Piano indagine già presente"


            );



        }








        piano.setStato(


                StatoPiano.ATTIVO


        );







        return repository.save(piano);



    }










    // =====================
    // MODIFICA
    // =====================


    @PutMapping("/{pianoId}")
    public PianoIndagine update(




            @PathVariable Long pianoId,


            @Valid

            @RequestBody PianoIndagine updatedPiano



    ){






        PianoIndagine piano =



                repository.findById(pianoId)



                        .orElseThrow(() ->



                                new ResponseStatusException(



                                        HttpStatus.NOT_FOUND,


                                        "Piano indagine non trovato"



                                )


                        );









        piano.setCodicePiano(


                updatedPiano.getCodicePiano()
                        .trim()
                        .toUpperCase()


        );







        piano.setRevisione(


                updatedPiano.getRevisione()


        );







        piano.setData(


                updatedPiano.getData()


        );








        piano.setDescrizione(


                updatedPiano.getDescrizione()


        );








        piano.setRedatto(


                updatedPiano.getRedatto()


        );








        piano.setVerificato(


                updatedPiano.getVerificato()


        );








        piano.setApprovato(


                updatedPiano.getApprovato()


        );








        piano.setAllegato(


                updatedPiano.getAllegato()


        );









        return repository.save(piano);



    }









    // =====================
    // ARCHIVIA
    // =====================


    @PutMapping("/{pianoId}/archivia")
    public PianoIndagine archivia(



            @PathVariable Long pianoId



    ){






        PianoIndagine piano =



                repository.findById(pianoId)



                        .orElseThrow(() ->



                                new ResponseStatusException(


                                        HttpStatus.NOT_FOUND,


                                        "Piano non trovato"


                                )


                        );










        piano.setStato(


                StatoPiano.ARCHIVIATO


        );










        return repository.save(piano);



    }









    // =====================
    // ELIMINA
    // =====================


    @DeleteMapping("/{pianoId}")
    public void delete(


            @PathVariable Long pianoId


    ){



        repository.deleteById(pianoId);



    }





}