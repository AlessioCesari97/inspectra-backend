package com.inspectra.backend.service;


import com.inspectra.backend.dto.EsecuzioneDetailDTO;
import com.inspectra.backend.dto.EsecuzioneListDTO;

import com.inspectra.backend.model.Esecuzione;

import com.inspectra.backend.repository.EsecuzioneRepository;

import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class EsecuzioneService {


    private final EsecuzioneRepository repository;



    public EsecuzioneService(
            EsecuzioneRepository repository
    ) {

        this.repository = repository;

    }




    // =========================
    // LISTA ESECUZIONI
    // =========================


    public List<EsecuzioneListDTO> getAllEsecuzioni() {


        return repository.findAll()

                .stream()

                .map(this::toListDTO)

                .toList();

    }





    // =========================
    // DETTAGLIO
    // =========================


    public EsecuzioneDetailDTO getEsecuzioneDetail(
            Long esecuzioneId
    ) {


        Esecuzione esecuzione =
                repository.findById(esecuzioneId)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Esecuzione non trovata"
                                )
                        );



        return toDetailDTO(esecuzione);

    }





    // =========================
    // LIST DTO
    // =========================


    public EsecuzioneListDTO toListDTO(
            Esecuzione esecuzione
    ) {


        EsecuzioneListDTO dto =
                new EsecuzioneListDTO();



        dto.setEsecuzioneId(
                esecuzione.getEsecuzioneId()
        );


        dto.setNumero(
                esecuzione.getNumero()
        );


        if(esecuzione.getStato() != null) {


            dto.setStato(
                    esecuzione.getStato().name()
            );

        }



        dto.setCampata(
                esecuzione.getCampata()
        );




        // PROVA

        if(esecuzione.getProva() != null) {


            dto.setProvaId(
                    esecuzione.getProva().getProvaId()
            );


            dto.setNomeProva(
                    esecuzione.getProva()
                            .getNomeProva()
            );


            dto.setSigla(
                    esecuzione.getProva()
                            .getSigla()
            );

        }
        // =====================
// ELEMENTO
// =====================

        if(esecuzione.getElemento() != null){



            dto.setElementoId(

                    esecuzione.getElemento()
                            .getElementoId()

            );




            dto.setCodiceElemento(

                    esecuzione.getElemento()
                            .getCodice()

            );





            dto.setCampata(

                    esecuzione.getElemento()
                            .getCampata()

            );



        }





        // PIANO

        if(esecuzione.getPianoIndagine() != null) {


            dto.setCodicePiano(

                    esecuzione
                            .getPianoIndagine()
                            .getCodicePiano()
            );

        }





        // ISPEZIONE

        if(esecuzione.getIspezione() != null) {


            dto.setTitoloIspezione(

                    esecuzione
                            .getIspezione()
                            .getTitoloIspezione()
            );

        }



        return dto;

    }







    // =========================
    // DETAIL DTO
    // =========================



    public EsecuzioneDetailDTO toDetailDTO(
            Esecuzione esecuzione
    ) {



        EsecuzioneDetailDTO dto =
                new EsecuzioneDetailDTO();




        dto.setEsecuzioneId(
                esecuzione.getEsecuzioneId()
        );


        dto.setNumero(
                esecuzione.getNumero()
        );



        if(esecuzione.getStato() != null) {


            dto.setStato(
                    esecuzione.getStato().name()
            );

        }




        dto.setNote(
                esecuzione.getNote()
        );


        dto.setPuntoPrevisto(
                esecuzione.getPuntoPrevisto()
        );


        if(esecuzione.getCampata() != null){


            dto.setCampata(

                    esecuzione.getCampata()

            );


        }else if(esecuzione.getElemento() != null){


            dto.setCampata(

                    esecuzione
                            .getElemento()
                            .getCampata()

            );


        }




        // GPS


        dto.setLatitudine(
                esecuzione.getLatitudine()
        );


        dto.setLongitudine(
                esecuzione.getLongitudine()
        );





        // TEMPO


        if(esecuzione.getTimestamp() != null) {


            dto.setTimestamp(

                    esecuzione
                            .getTimestamp()
                            .toString()
            );

        }





        // FOTO


        dto.setFotoPiano1(
                esecuzione.getFotoPiano1()
        );


        dto.setFotoPiano2(
                esecuzione.getFotoPiano2()
        );


        dto.setFotoPiano3(
                esecuzione.getFotoPiano3()
        );



        dto.setFotoCantiere1(
                esecuzione.getFotoCantiere1()
        );


        dto.setFotoCantiere2(
                esecuzione.getFotoCantiere2()
        );







        // PROVA


        if(esecuzione.getProva() != null) {



            dto.setProvaId(
                    esecuzione.getProva()
                            .getProvaId()
            );



            dto.setNomeProva(

                    esecuzione
                            .getProva()
                            .getNomeProva()
            );



            dto.setSigla(

                    esecuzione
                            .getProva()
                            .getSigla()
            );

        }







        // PIANO INDAGINE


        if(esecuzione.getPianoIndagine() != null) {



            dto.setPianoId(

                    esecuzione
                            .getPianoIndagine()
                            .getPianoId()
            );



            dto.setCodicePiano(

                    esecuzione
                            .getPianoIndagine()
                            .getCodicePiano()
            );




            if(esecuzione
                    .getPianoIndagine()
                    .getAsset() != null) {


                dto.setAssetId(

                        esecuzione
                                .getPianoIndagine()
                                .getAsset()
                                .getAssetId()
                );



                dto.setNomeViadotto(

                        esecuzione
                                .getPianoIndagine()
                                .getAsset()
                                .getNome()
                );

            }

        }







        // ELEMENTO


        if(esecuzione.getElemento() != null) {



            dto.setElementoId(

                    esecuzione
                            .getElemento()
                            .getElementoId()
            );



            dto.setCodiceElemento(

                    esecuzione
                            .getElemento()
                            .getCodice()
            );



            if(esecuzione.getElemento().getTipo() != null) {



                dto.setTipoElemento(

                        esecuzione
                                .getElemento()
                                .getTipo()
                                .getNome()
                );

            }

        }







        // ISPEZIONE


        if(esecuzione.getIspezione() != null) {



            dto.setIspezioneId(

                    esecuzione
                            .getIspezione()
                            .getIspezioneId()
            );



            dto.setTitoloIspezione(

                    esecuzione
                            .getIspezione()
                            .getTitoloIspezione()
            );

        }







        // SPOSTAMENTO


        if(esecuzione.getSpostatoElemento() != null) {



            dto.setSpostatoElementoId(

                    esecuzione
                            .getSpostatoElemento()
                            .getElementoId()
            );



            dto.setSpostatoElementoCodice(

                    esecuzione
                            .getSpostatoElemento()
                            .getCodice()
            );

        }



        dto.setSpostataSuCampata(

                esecuzione.getSpostataSuCampata()
        );



        return dto;

    }

}