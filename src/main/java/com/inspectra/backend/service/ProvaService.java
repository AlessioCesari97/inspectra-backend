package com.inspectra.backend.service;

import com.inspectra.backend.dto.ProvaDetailDTO;
import com.inspectra.backend.dto.ProvaListDTO;
import com.inspectra.backend.model.Prova;
import com.inspectra.backend.repository.ProvaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvaService {

    private final ProvaRepository repository;

    public ProvaService(ProvaRepository repository) {
        this.repository = repository;
    }


    // LISTA PROVE
    public List<ProvaListDTO> getAllProve() {

        return repository.findAll()
                .stream()
                .map(this::toListDTO)
                .toList();
    }


    // DETTAGLIO PROVA
    public ProvaDetailDTO getProvaDetail(Long provaId) {

        Prova prova = repository.findById(provaId)
                .orElseThrow(() ->
                        new RuntimeException("Prova non trovata")
                );

        return toDetailDTO(prova);
    }


    // LIST DTO
    public ProvaListDTO toListDTO(Prova prova) {

        ProvaListDTO dto =
                new ProvaListDTO();

        dto.setProvaId(
                prova.getProvaId()
        );

        dto.setNomeProva(
                prova.getNomeProva()
        );

        dto.setSigla(
                prova.getSigla()
        );

        return dto;
    }


    // DETAIL DTO
    public ProvaDetailDTO toDetailDTO(Prova prova) {

        ProvaDetailDTO dto =
                new ProvaDetailDTO();

        dto.setProvaId(
                prova.getProvaId()
        );

        dto.setNomeProva(
                prova.getNomeProva()
        );

        dto.setSigla(
                prova.getSigla()
        );

        dto.setDescrizione(
                prova.getDescrizione()
        );

        dto.setNoteGenerali(
                prova.getNoteGenerali()
        );

        return dto;
    }
}