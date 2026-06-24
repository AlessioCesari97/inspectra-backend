package com.inspectra.backend.service;

import com.inspectra.backend.dto.EsecuzioneListDTO;
import com.inspectra.backend.dto.IspezioneListDTO;
import com.inspectra.backend.dto.PianoIndagineDetailDTO;
import com.inspectra.backend.dto.PianoIndagineListDTO;
import com.inspectra.backend.model.PianoIndagine;
import com.inspectra.backend.model.StatoEsecuzione;
import com.inspectra.backend.model.StatoPiano;
import com.inspectra.backend.repository.PianoIndagineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PianoIndagineService {

    private final PianoIndagineRepository repository;

    public PianoIndagineService(
            PianoIndagineRepository repository
    ) {
        this.repository = repository;
    }


    // LISTA PIANI
    public List<PianoIndagineListDTO> getAllPiani() {

        return repository.findAll()
                .stream()
                .map(this::toListDTO)
                .toList();
    }


    // DETTAGLIO PIANO
    public PianoIndagineDetailDTO getPianoDetail(Long pianoId) {

        PianoIndagine piano = repository.findById(pianoId)
                .orElseThrow(() ->
                        new RuntimeException("Piano non trovato")
                );

        return toDetailDTO(piano);
    }


    // LIST DTO
    public PianoIndagineListDTO toListDTO(
            PianoIndagine piano
    ) {


        PianoIndagineListDTO dto =
                new PianoIndagineListDTO();


        dto.setPianoId(

                piano.getPianoId()

        );


        dto.setCodicePiano(

                piano.getCodicePiano()

        );


        dto.setRevisione(

                piano.getRevisione()

        );


        if (piano.getData() != null) {


            dto.setData(

                    piano.getData().toString()

            );


        }


        if (piano.getStato() != null) {


            dto.setStato(

                    piano.getStato().name()

            );


        }


        if (piano.getAsset() != null) {


            dto.setAssetId(

                    piano.getAsset()
                            .getAssetId()

            );


            dto.setAssetNome(

                    piano.getAsset()
                            .getNome()

            );


        }


        dto.setNumeroEsecuzioni(

                piano.getEsecuzioni() == null

                        ?

                        0

                        :

                        piano.getEsecuzioni().size()

        );


        dto.setNumeroIspezioni(

                piano.getIspezioni() == null

                        ?

                        0

                        :

                        piano.getIspezioni().size()

        );


        return dto;


    }


    // DETAIL DTO
    public PianoIndagineDetailDTO toDetailDTO(
            PianoIndagine piano
    ) {

        PianoIndagineDetailDTO dto =
                new PianoIndagineDetailDTO();

        dto.setPianoId(
                piano.getPianoId()
        );

        dto.setCodicePiano(
                piano.getCodicePiano()
        );

        dto.setRevisione(
                piano.getRevisione()
        );

        if (piano.getData() != null) {

            dto.setData(
                    piano.getData().toString()
            );
        }

        dto.setDescrizione(
                piano.getDescrizione()
        );

        dto.setRedatto(
                piano.getRedatto()
        );

        dto.setVerificato(
                piano.getVerificato()
        );

        dto.setApprovato(
                piano.getApprovato()
        );

        dto.setAllegato(
                piano.getAllegato()
        );

        if (piano.getStato() != null) {


            dto.setStato(

                    piano.getStato().name()

            );


        }


        // ASSET COLLEGATO
        if (piano.getAsset() != null) {

            dto.setAssetId(
                    piano.getAsset().getAssetId()
            );

            dto.setAssetNome(
                    piano.getAsset().getNome()
            );
        }


        // ESECUZIONI
        List<EsecuzioneListDTO> esecuzioni =

                piano.getEsecuzioni() == null

                        ? List.of()

                        : piano.getEsecuzioni()

                          .stream()

                          .map(e -> {


                              EsecuzioneListDTO esecuzioneDTO =
                              new EsecuzioneListDTO();


                              esecuzioneDTO.setEsecuzioneId(

                                      e.getEsecuzioneId()
                              );


                              esecuzioneDTO.setNumero(

                                      e.getNumero()
                              );


                              if (e.getStato() != null) {


                                  esecuzioneDTO.setStato(

                                          e.getStato().name()
                                  );

                              }


                              if (e.getElemento() != null) {


                                  esecuzioneDTO.setCampata(


                                          e.getElemento()
                                          .getCampata()


                                  );


                              }


                              // PROVA ASSOCIATA

                              if (e.getProva() != null) {


                                  esecuzioneDTO.setProvaId(

                                          e.getProva()
                                          .getProvaId()
                                  );


                                  esecuzioneDTO.setNomeProva(

                                          e.getProva()
                                          .getNomeProva()
                                  );


                                  esecuzioneDTO.setSigla(

                                          e.getProva()
                                          .getSigla()
                                  );

                              }

                              // ELEMENTO ASSOCIATO

                              if (e.getElemento() != null) {


                                  esecuzioneDTO.setElementoId(


                                          e.getElemento()
                                          .getElementoId()


                                  );


                                  esecuzioneDTO.setCodiceElemento(


                                          e.getElemento()
                                          .getCodice()


                                  );


                              }


                              // PIANO

                              esecuzioneDTO.setCodicePiano(

                                      piano.getCodicePiano()
                              );


                              // ISPEZIONE

                              if (e.getIspezione() != null) {


                                  esecuzioneDTO.setTitoloIspezione(

                                          e.getIspezione()
                                          .getTitoloIspezione()
                                  );

                              }
                              if (piano.getStato() != null) {


                                  dto.setStato(

                                          piano.getStato().name()

                                  );


                              }


                              return esecuzioneDTO;

                          })

                          .toList();


        dto.setEsecuzioni(esecuzioni);

// ISPEZIONI
        List<IspezioneListDTO> ispezioni =

                piano.getIspezioni() == null

                        ? List.of()

                        : piano.getIspezioni()
                          .stream()
                          .map(i -> {


                              IspezioneListDTO ispezioneDTO =
                              new IspezioneListDTO();


                              ispezioneDTO.setIspezioneId(
                                      i.getIspezioneId()
                              );


                              ispezioneDTO.setTitoloIspezione(
                                      i.getTitoloIspezione()
                              );


                              if (i.getDataIspezione() != null) {

                                  ispezioneDTO.setDataIspezione(
                                          i.getDataIspezione()
                                          .toString()
                                  );
                              }


                              if (i.getStato() != null) {

                                  ispezioneDTO.setStato(
                                          i.getStato()
                                          .toString()
                                  );
                              }


                              return ispezioneDTO;

                          })
                          .toList();


        dto.setIspezioni(ispezioni);
        return dto;
    }


    public void aggiornaStato(PianoIndagine piano) {


        if (

                piano.getStato()

                        ==

                        StatoPiano.ARCHIVIATO

        ) {


            return;


        }


        boolean aperto =


                piano.getEsecuzioni()

                        .stream()

                        .anyMatch(e ->


                                e.getStato()

                                        ==

                                        StatoEsecuzione.PREVISTA


                        );


        if (aperto) {


            piano.setStato(

                    StatoPiano.ATTIVO

            );


        } else {


            piano.setStato(

                    StatoPiano.COMPLETATO

            );


        }


        repository.save(

                piano

        );


    }
}