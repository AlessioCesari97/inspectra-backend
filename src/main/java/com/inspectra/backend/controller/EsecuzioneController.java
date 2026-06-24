package com.inspectra.backend.controller;


import com.inspectra.backend.dto.EsecuzioneDetailDTO;
import com.inspectra.backend.dto.EsecuzioneListDTO;

import com.inspectra.backend.model.Esecuzione;
import com.inspectra.backend.model.Ispezione;

import com.inspectra.backend.model.PianoIndagine;
import com.inspectra.backend.repository.EsecuzioneRepository;
import com.inspectra.backend.repository.IspezioneRepository;
import com.inspectra.backend.repository.PianoIndagineRepository;

import com.inspectra.backend.service.EsecuzioneService;

import com.inspectra.backend.model.StatoEsecuzione;


import com.inspectra.backend.service.PianoIndagineService;
import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController
@RequestMapping("/esecuzioni")
@CrossOrigin("*")
public class EsecuzioneController {


    private final EsecuzioneService service;


    private final EsecuzioneRepository repository;


    private final IspezioneRepository ispezioneRepository;


    private final PianoIndagineService pianoIndagineService;

    private final PianoIndagineRepository  pianoIndagineRepository;


    public EsecuzioneController(

            EsecuzioneService service,

            EsecuzioneRepository repository,

            IspezioneRepository ispezioneRepository,

            PianoIndagineService pianoIndagineService,

            PianoIndagineRepository pianoIndagineRepository

    ) {


        this.service = service;


        this.repository = repository;


        this.ispezioneRepository =
                ispezioneRepository;

        this.pianoIndagineService=pianoIndagineService;


        this.pianoIndagineRepository=pianoIndagineRepository;

    }








    // =========================
    // LISTA ESECUZIONI
    // =========================


    @GetMapping
    public List<EsecuzioneListDTO> getAll() {


        return service.getAllEsecuzioni();

    }









    // =========================
    // DETTAGLIO ESECUZIONE
    // =========================


    @GetMapping("/{esecuzioneId}")
    public EsecuzioneDetailDTO getDetail(

            @PathVariable Long esecuzioneId
    ) {


        return service.getEsecuzioneDetail(
                esecuzioneId
        );

    }









    // =========================
    // CREAZIONE ESECUZIONE
    // =========================

    @PostMapping
    public EsecuzioneDetailDTO  create(

            @RequestBody Esecuzione esecuzione

    ) {




        if(

                esecuzione.getNumero() == null

                        ||

                        esecuzione.getPianoIndagine() == null

                        ||

                        esecuzione.getPianoIndagine().getPianoId() == null

                        ||

                        esecuzione.getProva() == null

                        ||

                        esecuzione.getProva().getProvaId() == null

                        ||

                        esecuzione.getElemento() == null

                        ||

                        esecuzione.getElemento().getElementoId() == null

                        ||

                        esecuzione.getPuntoPrevisto() == null

                        ||

                        esecuzione.getPuntoPrevisto().isBlank()

        ){



            throw new RuntimeException(

                    "Campi obbligatori mancanti"

            );


        }

        if(

                esecuzione.getNumero() <= 0

        ){


            throw new RuntimeException(

                    "Il numero prova deve essere positivo"

            );


        }








        boolean exists =


                repository.existsByNumeroAndProva_ProvaIdAndPianoIndagine_PianoId(


                        esecuzione.getNumero(),



                        esecuzione
                                .getProva()
                                .getProvaId(),



                        esecuzione
                                .getPianoIndagine()
                                .getPianoId()


                );








        if(exists){



            throw new RuntimeException(

                    "Numero esecuzione già presente nel piano"

            );


        }









        if(

                esecuzione.getStato() == null

        ){



            esecuzione.setStato(

                    StatoEsecuzione.PREVISTA

            );



        }








        Esecuzione salvata =

                repository.save(

                        esecuzione

                );







        PianoIndagine piano =

                pianoIndagineRepository

                        .findById(

                                salvata
                                        .getPianoIndagine()
                                        .getPianoId()

                        )

                        .orElseThrow();




        pianoIndagineService.aggiornaStato(

                piano

        );








        return service.toDetailDTO(salvata);



    }









    // =========================
    // MODIFICA ESECUZIONE
    // =========================


    @PutMapping("/{esecuzioneId}")
    public EsecuzioneDetailDTO update(


            @PathVariable Long esecuzioneId,


            @RequestBody Esecuzione updatedEsecuzione

    ) {




        Esecuzione esecuzione =

                repository.findById(esecuzioneId)


                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Esecuzione non trovata"

                                )

                        );









        // =========================
        // VALIDAZIONE NUMERO
        // =========================



        if(

                updatedEsecuzione.getNumero() == null

                        ||

                        updatedEsecuzione.getNumero() <= 0

        ){



            throw new RuntimeException(

                    "Il numero prova deve essere positivo"

            );



        }










        boolean exists =


                repository

                        .existsByNumeroAndProva_ProvaIdAndPianoIndagine_PianoIdAndEsecuzioneIdNot(


                                updatedEsecuzione.getNumero(),



                                updatedEsecuzione
                                        .getProva()
                                        .getProvaId(),



                                updatedEsecuzione
                                        .getPianoIndagine()
                                        .getPianoId(),



                                esecuzioneId


                        );








        if(exists){



            throw new RuntimeException(

                    "Esiste già una prova con questo numero"

            );



        }












        // =========================
        // DATI BASE
        // =========================



        esecuzione.setNumero(

                updatedEsecuzione.getNumero()

        );



        esecuzione.setStato(

                updatedEsecuzione.getStato()

        );



        esecuzione.setPuntoPrevisto(

                updatedEsecuzione.getPuntoPrevisto()

        );



        esecuzione.setNote(

                updatedEsecuzione.getNote()

        );










        // =========================
        // DATA
        // =========================



        esecuzione.setTimestamp(

                updatedEsecuzione.getTimestamp()

        );










        // =========================
        // GPS
        // =========================



        esecuzione.setLatitudine(

                updatedEsecuzione.getLatitudine()

        );



        esecuzione.setLongitudine(

                updatedEsecuzione.getLongitudine()

        );










        // =========================
        // FOTO PIANO
        // =========================



        esecuzione.setFotoPiano1(

                updatedEsecuzione.getFotoPiano1()

        );



        esecuzione.setFotoPiano2(

                updatedEsecuzione.getFotoPiano2()

        );



        esecuzione.setFotoPiano3(

                updatedEsecuzione.getFotoPiano3()

        );










        // =========================
        // FOTO CANTIERE
        // =========================



        esecuzione.setFotoCantiere1(

                updatedEsecuzione.getFotoCantiere1()

        );



        esecuzione.setFotoCantiere2(

                updatedEsecuzione.getFotoCantiere2()

        );












        // =========================
        // RELAZIONI FISSE
        // =========================



        esecuzione.setPianoIndagine(

                updatedEsecuzione.getPianoIndagine()

        );



        esecuzione.setProva(

                updatedEsecuzione.getProva()

        );



        esecuzione.setElemento(

                updatedEsecuzione.getElemento()

        );











        // =========================
        // ISPEZIONE
        // =========================



        if(

                updatedEsecuzione.getIspezione() != null

                        &&

                        updatedEsecuzione
                                .getIspezione()
                                .getIspezioneId() != null

        ){



            Ispezione ispezione =

                    ispezioneRepository

                            .findById(

                                    updatedEsecuzione
                                            .getIspezione()
                                            .getIspezioneId()

                            )

                            .orElseThrow();




            esecuzione.setIspezione(

                    ispezione

            );



        }else{



            esecuzione.setIspezione(null);



        }












        // =========================
        // SPOSTAMENTO
        // =========================



        esecuzione.setSpostatoElemento(

                updatedEsecuzione.getSpostatoElemento()

        );












        // =========================
        // PULIZIA STATI
        // =========================







        if(

                esecuzione.getStato()

                        ==

                        StatoEsecuzione.PREVISTA

        ){



            esecuzione.setTimestamp(null);


            esecuzione.setFotoCantiere1(null);


            esecuzione.setFotoCantiere2(null);



            esecuzione.setLatitudine(null);


            esecuzione.setLongitudine(null);



            esecuzione.setSpostatoElemento(null);



        }









        if(

                esecuzione.getStato()

                        ==

                        StatoEsecuzione.NON_ESEGUIBILE

        ){



            esecuzione.setFotoCantiere1(null);


            esecuzione.setFotoCantiere2(null);



            esecuzione.setLatitudine(null);


            esecuzione.setLongitudine(null);



            esecuzione.setSpostatoElemento(null);



        }










        if(

                esecuzione.getStato()

                        ==

                        StatoEsecuzione.SPOSTATA

        ){



            esecuzione.setFotoCantiere1(null);


            esecuzione.setFotoCantiere2(null);



            esecuzione.setLatitudine(null);


            esecuzione.setLongitudine(null);



        }










        if(

                esecuzione.getStato()

                        ==

                        StatoEsecuzione.ESEGUITA

                        ||

                        esecuzione.getStato()

                                ==

                                StatoEsecuzione.AGGIUNTA_IN_SITO

        ){



            esecuzione.setSpostatoElemento(null);



        }









        Esecuzione salvata =

                repository.save(esecuzione);










        PianoIndagine piano =

                pianoIndagineRepository

                        .findById(

                                salvata
                                        .getPianoIndagine()
                                        .getPianoId()

                        )

                        .orElseThrow();







        pianoIndagineService.aggiornaStato(

                piano

        );









        return service.toDetailDTO(salvata);



    }


    // =========================
    // PROVE DISPONIBILI PIANO
    // =========================


    @GetMapping("/piano/{pianoId}/disponibili")
    public List<EsecuzioneListDTO> getDisponibili(

            @PathVariable Long pianoId

    ){


        return repository

                .findByPianoIndaginePianoIdAndIspezioneIsNull(
                        pianoId
                )

                .stream()

                .map(service::toListDTO)

                .toList();


    }









    // =========================
    // ASSOCIA A ISPEZIONE
    // =========================


    @PutMapping("/{esecuzioneId}/associa-ispezione/{ispezioneId}")
    public EsecuzioneDetailDTO associaIspezione(


            @PathVariable Long esecuzioneId,


            @PathVariable Long ispezioneId

    ){



        Esecuzione esecuzione =


                repository.findById(esecuzioneId)


                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Esecuzione non trovata"

                                )

                        );







        Ispezione ispezione =


                ispezioneRepository.findById(ispezioneId)


                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Ispezione non trovata"

                                )

                        );








        esecuzione.setIspezione(

                ispezione

        );








        Esecuzione salvata =


                repository.save(esecuzione);








        return service.toDetailDTO(

                salvata

        );



    }



    // =========================
    // ELIMINAZIONE ESECUZIONE
    // =========================


    @DeleteMapping("/{esecuzioneId}")
    public void delete(

            @PathVariable Long esecuzioneId
    ) {



        repository.deleteById(
                esecuzioneId
        );

    }


}