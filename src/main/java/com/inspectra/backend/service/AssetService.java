package com.inspectra.backend.service;

import com.inspectra.backend.dto.*;
import com.inspectra.backend.model.Asset;
import com.inspectra.backend.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository repository;

    public AssetService(AssetRepository repository) {
        this.repository = repository;
    }


    public List<AssetListDTO> getAllAssets() {

        return repository.findAll()
                .stream()
                .map(this::toListDTO)
                .toList();
    }


    public AssetDetailDTO getAssetDetail(Long assetId) {

        Asset asset = repository.findById(assetId)
                .orElseThrow(() ->
                        new RuntimeException("Asset non trovato")
                );

        return toDetailDTO(asset);
    }


    public AssetListDTO toListDTO(Asset asset) {

        AssetListDTO dto = new AssetListDTO();

        dto.setAssetId(asset.getAssetId());

        dto.setNome(asset.getNome());

        dto.setStrada(asset.getStrada());

        dto.setGestore(asset.getGestore());

        dto.setNumeroPiani(asset.getPiani().size());

        dto.setNumeroElementi(asset.getElementi().size());

        dto.setNumeroIspezioni(asset.getIspezioni().size());

        return dto;
    }


    public AssetDetailDTO toDetailDTO(Asset asset) {

        AssetDetailDTO dto = new AssetDetailDTO();

        dto.setAssetId(asset.getAssetId());

        dto.setNome(asset.getNome());

        dto.setStrada(asset.getStrada());

        dto.setGestore(asset.getGestore());

        dto.setPosizione(asset.getPosizione());

        dto.setNote(asset.getNote());


        // PIANI INDAGINE
        List<PianoIndagineListDTO> piani = asset.getPiani()
                .stream()
                .map(p -> {

                    PianoIndagineListDTO pianoDTO =
                            new PianoIndagineListDTO();

                    pianoDTO.setPianoId(
                            p.getPianoId()
                    );

                    pianoDTO.setCodicePiano(
                            p.getCodicePiano()
                    );

                    pianoDTO.setRevisione(
                            p.getRevisione()
                    );

                    if (p.getData() != null) {

                        pianoDTO.setData(
                                p.getData().toString()
                        );
                    }

                    if (p.getStato() != null) {


                        pianoDTO.setStato(

                                p.getStato().name()

                        );


                    }


                    return pianoDTO;
                })
                .toList();

        dto.setPiani(piani);


        // ELEMENTI
        List<ElementoListDTO> elementi =

                asset.getElementi()
                        .stream()
                        .map(e -> {

                            ElementoListDTO elementoDTO =
                                    new ElementoListDTO();

                            elementoDTO.setElementoId(
                                    e.getElementoId()
                            );

                            elementoDTO.setCodice(
                                    e.getCodice()
                            );

                            if (e.getTipo() != null) {

                                elementoDTO.setTipoElementoId(
                                        e.getTipo()
                                                .getTipoElementoId()
                                );

                                elementoDTO.setTipoElementoNome(
                                        e.getTipo()
                                                .getNome()
                                );
                            }

                            elementoDTO.setCampata(
                                    e.getCampata()
                            );

                            if (e.getLato() != null) {

                                elementoDTO.setLato(
                                        e.getLato().name()
                                );
                            }

                            return elementoDTO;
                        })
                        .toList();

        dto.setElementi(elementi);


        // ISPEZIONI
        List<IspezioneListDTO> ispezioni =
                asset.getIspezioni()
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
}
