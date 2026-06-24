package com.inspectra.backend.controller;



import com.inspectra.backend.dto.ProvaDetailDTO;

import com.inspectra.backend.dto.ProvaListDTO;


import com.inspectra.backend.model.Prova;


import com.inspectra.backend.repository.ProvaRepository;


import com.inspectra.backend.service.ProvaService;



import jakarta.validation.Valid;



import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.*;


import org.springframework.web.server.ResponseStatusException;



import java.util.List;






@RestController
@RequestMapping("/prove")
@CrossOrigin("*")
public class ProvaController {





    private final ProvaService service;


    private final ProvaRepository repository;






    public ProvaController(

            ProvaService service,

            ProvaRepository repository

    ){


        this.service = service;


        this.repository = repository;


    }



    // =====================
    // LISTA PROVE
    // =====================


    @GetMapping
    public List<ProvaListDTO> getAll(){



        return service.getAllProve();



    }










    // =====================
    // DETTAGLIO PROVA
    // =====================



    @GetMapping("/{provaId}")
    public ProvaDetailDTO getDetail(


            @PathVariable Long provaId

    ){



        return service.getProvaDetail(

                provaId

        );



    }





    // =====================
    // CREA PROVA
    // =====================



    @PostMapping
    public Prova create(



            @Valid
            @RequestBody Prova prova


    ){








        prova.setNomeProva(



                prova.getNomeProva()

                        .trim()



        );








        prova.setSigla(



                prova.getSigla()

                        .trim()

                        .toUpperCase()



        );









        if(

                repository.existsByNomeProvaIgnoreCase(

                        prova.getNomeProva()

                )

        ){



            throw new ResponseStatusException(


                    HttpStatus.CONFLICT,


                    "Nome prova già presente"


            );



        }











        if(

                repository.existsBySiglaIgnoreCase(

                        prova.getSigla()

                )

        ){



            throw new ResponseStatusException(


                    HttpStatus.CONFLICT,


                    "Sigla già presente"


            );



        }








        return repository.save(

                prova

        );



    }











    // =====================
    // MODIFICA PROVA
    // =====================



    @PutMapping("/{provaId}")
    public Prova update(


            @PathVariable Long provaId,


            @Valid

            @RequestBody Prova updatedProva


    ){







        Prova prova =


                repository.findById(provaId)



                        .orElseThrow(() ->



                                new ResponseStatusException(


                                        HttpStatus.NOT_FOUND,


                                        "Prova non trovata"


                                )


                        );










        prova.setNomeProva(


                updatedProva.getNomeProva()

                        .trim()


        );






        prova.setSigla(


                updatedProva.getSigla()

                        .trim()

                        .toUpperCase()


        );







        prova.setDescrizione(


                updatedProva.getDescrizione()


        );







        prova.setNoteGenerali(


                updatedProva.getNoteGenerali()


        );








        return repository.save(prova);



    }










    // =====================
    // ELIMINA PROVA
    // =====================


    @DeleteMapping("/{provaId}")
    public void delete(


            @PathVariable Long provaId

    ){



        repository.deleteById(

                provaId

        );



    }




}