package com.inspectra.backend.service;

import com.inspectra.backend.dto.EsecuzioneListDTO;
import com.inspectra.backend.dto.IspezioneDetailDTO;
import com.inspectra.backend.dto.IspezioneListDTO;
import com.inspectra.backend.model.Ispezione;
import com.inspectra.backend.repository.IspezioneRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IspezioneService {


    private final IspezioneRepository repository;


    public IspezioneService(
            IspezioneRepository repository
    ) {

        this.repository = repository;
    }



    // LISTA

    public List<IspezioneListDTO> getAllIspezioni() {


        return repository.findAll()
                .stream()
                .map(this::toListDTO)
                .toList();

    }



    // DETAIL

    public IspezioneDetailDTO getIspezioneDetail(
            Long ispezioneId
    ) {


        Ispezione ispezione =
                repository.findById(ispezioneId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Ispezione non trovata"
                                )
                        );


        return toDetailDTO(ispezione);
    }





    // LIST DTO

    public IspezioneListDTO toListDTO(
            Ispezione ispezione
    ) {


        IspezioneListDTO dto =
                new IspezioneListDTO();



        dto.setIspezioneId(
                ispezione.getIspezioneId()
        );


        dto.setTitoloIspezione(
                ispezione.getTitoloIspezione()
        );



        if (ispezione.getDataIspezione() != null) {

            dto.setDataIspezione(
                    ispezione.getDataIspezione()
                            .toString()
            );
        }



        if (ispezione.getStato() != null) {

            dto.setStato(
                    ispezione.getStato()
                            .toString()
            );
        }



        if (ispezione.getAsset() != null) {

            dto.setAssetNome(
                    ispezione.getAsset()
                            .getNome()
            );
        }

        if(

                ispezione.getEsecuzioni() != null

        ){


            dto.setNumeroProve(

                    ispezione
                            .getEsecuzioni()
                            .size()

            );


        }else{


            dto.setNumeroProve(0);


        }

        if(ispezione.getPianoIndagine()!=null){

            dto.setCodicePiano(

                    ispezione.getPianoIndagine()
                            .getCodicePiano()

            );

        }



        return dto;
    }






    // DETAIL DTO

    public IspezioneDetailDTO toDetailDTO(
            Ispezione ispezione
    ) {


        IspezioneDetailDTO dto =
                new IspezioneDetailDTO();



        dto.setIspezioneId(
                ispezione.getIspezioneId()
        );


        dto.setTitoloIspezione(
                ispezione.getTitoloIspezione()
        );



        if (ispezione.getDataIspezione() != null) {

            dto.setDataIspezione(
                    ispezione.getDataIspezione()
                            .toString()
            );
        }



        if (ispezione.getStato() != null) {

            dto.setStato(
                    ispezione.getStato()
                            .toString()
            );
        }



        dto.setOperatoreProve(
                ispezione.getOperatoreProve()
        );

        dto.setIngegnere(
                ispezione.getIngegnere()
        );

        dto.setReferenteConcessionaria(
                ispezione.getReferenteConcessionaria()
        );



        if (ispezione.getInstallazioneCantiere() != null) {

            dto.setInstallazioneCantiere(
                    ispezione.getInstallazioneCantiere()
                            .toString()
            );
        }


        if (ispezione.getInizioLavori() != null) {

            dto.setInizioLavori(
                    ispezione.getInizioLavori()
                            .toString()
            );
        }


        if (ispezione.getFineLavori() != null) {

            dto.setFineLavori(
                    ispezione.getFineLavori()
                            .toString()
            );
        }



        dto.setFirmaOperatore(
                ispezione.getFirmaOperatore()
        );

        dto.setFirmaIngegnere(
                ispezione.getFirmaIngegnere()
        );

        dto.setFirmaConcessionaria(
                ispezione.getFirmaConcessionaria()
        );


        dto.setCreatedBy(
                ispezione.getCreatedBy()
        );


        dto.setAnnotazioniAggiuntive(
                ispezione.getAnnotazioniAggiuntive()
        );


        dto.setReport(
                ispezione.getReport()
        );



        // ASSET

        if (ispezione.getAsset() != null) {

            dto.setAssetId(
                    ispezione.getAsset()
                            .getAssetId()
            );

            dto.setAssetNome(
                    ispezione.getAsset()
                            .getNome()
            );
        }



        // PIANO

        if (ispezione.getPianoIndagine() != null) {

            dto.setPianoId(
                    ispezione.getPianoIndagine()
                            .getPianoId()
            );

            dto.setCodicePiano(
                    ispezione.getPianoIndagine()
                            .getCodicePiano()
            );
        }



        // ESECUZIONI
        List<EsecuzioneListDTO> esecuzioni =

                ispezione.getEsecuzioni() == null

                        ? List.of()

                        : ispezione.getEsecuzioni()

                          .stream()

                          .map(e -> {


                              EsecuzioneListDTO ex =
                              new EsecuzioneListDTO();



                              ex.setEsecuzioneId(

                                      e.getEsecuzioneId()
                              );



                              ex.setNumero(

                                      e.getNumero()
                              );



                              if (e.getStato() != null) {


                                  ex.setStato(

                                          e.getStato().name()
                                  );

                              }



                              // PROVA

                              if (e.getProva() != null) {


                                  ex.setProvaId(

                                          e.getProva()
                                          .getProvaId()
                                  );


                                  ex.setNomeProva(

                                          e.getProva()
                                          .getNomeProva()
                                  );


                                  ex.setSigla(

                                          e.getProva()
                                          .getSigla()
                                  );

                              }



                              // PIANO

                              if (e.getPianoIndagine() != null) {


                                  ex.setCodicePiano(

                                          e.getPianoIndagine()
                                          .getCodicePiano()
                                  );

                              }

                              // ELEMENTO

                              if(e.getElemento() != null){


                                  ex.setElementoId(

                                          e.getElemento()
                                          .getElementoId()

                                  );



                                  ex.setCodiceElemento(

                                          e.getElemento()
                                          .getCodice()

                                  );



                                  ex.setCampata(

                                          e.getElemento()
                                          .getCampata()

                                  );


                              }



                              return ex;

                          })

                          .toList();



        dto.setEsecuzioni(esecuzioni);



        return dto;
    }
}