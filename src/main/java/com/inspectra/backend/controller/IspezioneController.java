package com.inspectra.backend.controller;

import com.inspectra.backend.dto.IspezioneDetailDTO;
import com.inspectra.backend.dto.IspezioneListDTO;
import com.inspectra.backend.model.Ispezione;
import com.inspectra.backend.model.StatoIspezione;
import com.inspectra.backend.repository.IspezioneRepository;
import com.inspectra.backend.service.IspezioneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ispezioni")
@CrossOrigin("*")
public class IspezioneController {

    private final IspezioneService service;

    private final IspezioneRepository repository;


    public IspezioneController(
            IspezioneService service,
            IspezioneRepository repository
    ) {

        this.service = service;
        this.repository = repository;
    }


    // LISTA ISPEZIONI
    @GetMapping
    public List<IspezioneListDTO> getAll() {

        return service.getAllIspezioni();
    }


    // DETTAGLIO ISPEZIONE
    @GetMapping("/{ispezioneId}")
    public IspezioneDetailDTO getDetail(
            @PathVariable Long ispezioneId
    ) {

        return service.getIspezioneDetail(ispezioneId);
    }


    // =========================
// CREAZIONE ISPEZIONE
// =========================

    @PostMapping
    public Ispezione create(

            @RequestBody Ispezione ispezione

    ) {



        if(

                ispezione.getTitoloIspezione() == null

                        ||

                        ispezione.getTitoloIspezione().isBlank()

        ){


            throw new RuntimeException(

                    "Titolo ispezione obbligatorio"

            );


        }





        if(ispezione.getStato() == null){


            ispezione.setStato(

                    StatoIspezione.BOZZA

            );


        }




        return repository.save(

                ispezione

        );

    }


    // =========================
// MODIFICA ISPEZIONE
// =========================


    @PutMapping("/{ispezioneId}")
    public Ispezione update(


            @PathVariable Long ispezioneId,


            @RequestBody Ispezione updatedIspezione

    ){



        Ispezione ispezione =


                repository.findById(ispezioneId)


                        .orElseThrow(() ->


                                new RuntimeException(

                                        "Ispezione non trovata"

                                )

                        );







        if(

                updatedIspezione.getTitoloIspezione() == null

                        ||

                        updatedIspezione
                                .getTitoloIspezione()
                                .isBlank()

        ){



            throw new RuntimeException(

                    "Titolo obbligatorio"

            );


        }







        // DATI GENERALI


        ispezione.setTitoloIspezione(

                updatedIspezione.getTitoloIspezione()

        );



        ispezione.setDataIspezione(

                updatedIspezione.getDataIspezione()

        );



        ispezione.setStato(

                updatedIspezione.getStato()

        );




        ispezione.setOperatoreProve(

                updatedIspezione.getOperatoreProve()

        );



        ispezione.setIngegnere(

                updatedIspezione.getIngegnere()

        );



        ispezione.setReferenteConcessionaria(

                updatedIspezione.getReferenteConcessionaria()

        );




        ispezione.setCreatedBy(

                updatedIspezione.getCreatedBy()

        );




        ispezione.setAnnotazioniAggiuntive(

                updatedIspezione.getAnnotazioniAggiuntive()

        );







        // LAVORI


        ispezione.setInstallazioneCantiere(

                updatedIspezione.getInstallazioneCantiere()

        );



        ispezione.setInizioLavori(

                updatedIspezione.getInizioLavori()

        );



        ispezione.setFineLavori(

                updatedIspezione.getFineLavori()

        );







        // FIRME


        ispezione.setFirmaOperatore(

                updatedIspezione.getFirmaOperatore()

        );



        ispezione.setFirmaIngegnere(

                updatedIspezione.getFirmaIngegnere()

        );



        ispezione.setFirmaConcessionaria(

                updatedIspezione.getFirmaConcessionaria()

        );





        ispezione.setReport(

                updatedIspezione.getReport()

        );







        // RELAZIONI


        ispezione.setAsset(

                updatedIspezione.getAsset()

        );



        ispezione.setPianoIndagine(

                updatedIspezione.getPianoIndagine()

        );







        // =========================
        // REGOLE STATI
        // =========================



        if(

                ispezione.getStato()

                        ==

                        StatoIspezione.BOZZA

        ){



            ispezione.setInstallazioneCantiere(null);


            ispezione.setInizioLavori(null);


            ispezione.setFineLavori(null);



            ispezione.setFirmaOperatore(null);


            ispezione.setFirmaIngegnere(null);


            ispezione.setFirmaConcessionaria(null);



            ispezione.setReport(null);


        }







        if(

                ispezione.getStato()

                        ==

                        StatoIspezione.IN_CORSO

        ){



            if(

                    ispezione.getInstallazioneCantiere()==null

                            ||

                            ispezione.getInizioLavori()==null

            ){


                throw new RuntimeException(

                        "Inserire dati inizio lavori"

                );


            }





            ispezione.setFirmaOperatore(null);


            ispezione.setFirmaIngegnere(null);


            ispezione.setFirmaConcessionaria(null);


            ispezione.setReport(null);



        }








        if(

                ispezione.getStato()

                        ==

                        StatoIspezione.COMPLETATA

        ){



            if(ispezione.getFineLavori()==null){


                throw new RuntimeException(

                        "Inserire fine lavori"

                );


            }



            ispezione.setReport(null);



        }










        if(

                ispezione.getStato()

                        ==

                        StatoIspezione.FIRMATA

        ){



            if(

                    ispezione.getFirmaOperatore()==null

                            ||

                            ispezione.getFirmaIngegnere()==null

                            ||

                            ispezione.getFirmaConcessionaria()==null

            ){



                throw new RuntimeException(

                        "Firme obbligatorie"

                );


            }


        }








        return repository.save(

                ispezione

        );



    }


    // ELIMINAZIONE ISPEZIONE
    @DeleteMapping("/{ispezioneId}")
    public void delete(
            @PathVariable Long ispezioneId
    ) {

        repository.deleteById(ispezioneId);
    }
}