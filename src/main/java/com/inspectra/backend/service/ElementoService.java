package com.inspectra.backend.service;


import com.inspectra.backend.dto.ElementoDetailDTO;

import com.inspectra.backend.dto.ElementoListDTO;

import com.inspectra.backend.dto.EsecuzioneListDTO;


import com.inspectra.backend.model.Elemento;


import com.inspectra.backend.repository.ElementoRepository;


import org.springframework.stereotype.Service;


import java.util.List;





@Service
public class ElementoService {



    private final ElementoRepository repository;





    public ElementoService(

            ElementoRepository repository

    ) {


        this.repository = repository;


    }









    // =========================
    // LISTA ELEMENTI
    // =========================


    public List<ElementoListDTO> getAllElementi() {



        return repository.findAll()


                .stream()


                .map(this::toListDTO)


                .toList();


    }









    // =========================
    // DETTAGLIO ELEMENTO
    // =========================


    public ElementoDetailDTO getElementoDetail(


            Long elementoId

    ) {




        Elemento elemento =


                repository.findById(elementoId)



                        .orElseThrow(() ->



                                new RuntimeException(

                                        "Elemento non trovato"

                                )

                        );





        return toDetailDTO(elemento);



    }











    // =========================
    // LIST DTO
    // =========================


    public ElementoListDTO toListDTO(


            Elemento elemento

    ) {





        ElementoListDTO dto =


                new ElementoListDTO();







        dto.setElementoId(


                elemento.getElementoId()


        );






        dto.setCodice(


                elemento.getCodice()


        );









        // TIPO ELEMENTO


        if(elemento.getTipo() != null){





            dto.setTipoElementoId(



                    elemento.getTipo()

                            .getTipoElementoId()


            );






            dto.setTipoElementoNome(



                    elemento.getTipo()

                            .getNome()


            );



        }










        dto.setCampata(


                elemento.getCampata()


        );







        if(elemento.getLato() != null){



            dto.setLato(


                    elemento.getLato()

                            .name()


            );



        }







        return dto;



    }












    // =========================
    // DETAIL DTO
    // =========================


    public ElementoDetailDTO toDetailDTO(


            Elemento elemento

    ) {





        ElementoDetailDTO dto =


                new ElementoDetailDTO();








        dto.setElementoId(


                elemento.getElementoId()


        );






        dto.setCodice(


                elemento.getCodice()


        );









        // TIPO ELEMENTO


        if(elemento.getTipo() != null){






            dto.setTipoElementoId(




                    elemento.getTipo()

                            .getTipoElementoId()



            );








            dto.setTipoElementoNome(




                    elemento.getTipo()

                            .getNome()



            );



        }










        dto.setCampata(



                elemento.getCampata()



        );








        if(elemento.getLato() != null){





            dto.setLato(



                    elemento.getLato()

                            .name()



            );



        }










        dto.setDescrizione(



                elemento.getDescrizione()



        );






        dto.setFoto(



                elemento.getFoto()



        );






        dto.setLatitudine(



                elemento.getLatitudine()



        );






        dto.setLongitudine(



                elemento.getLongitudine()



        );











        // =========================
        // OPERA COLLEGATA
        // =========================


        if(elemento.getAsset() != null){






            dto.setAssetId(



                    elemento.getAsset()

                            .getAssetId()



            );








            dto.setNome(



                    elemento.getAsset()

                            .getNome()



            );



        }













        // =========================
        // STORICO ESECUZIONI
        // =========================




        List<EsecuzioneListDTO> esecuzioni =




                elemento.getEsecuzioni() == null




                        ?



                        List.of()




                        :



                        elemento.getEsecuzioni()



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









                            if(e.getStato() != null){






                                esecuzioneDTO.setStato(





                                        e.getStato()

                                        .name()




                                );



                            }











                            if(e.getProva() != null){






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











                            if(e.getPianoIndagine() != null){






                                esecuzioneDTO.setCodicePiano(




                                        e.getPianoIndagine()

                                        .getCodicePiano()



                                );



                            }









                            if(e.getIspezione() != null){






                                esecuzioneDTO.setTitoloIspezione(




                                        e.getIspezione()

                                        .getTitoloIspezione()



                                );



                            }









                            return esecuzioneDTO;




                        })



                        .toList();










        dto.setEsecuzioni(


                esecuzioni


        );







        return dto;



    }



}