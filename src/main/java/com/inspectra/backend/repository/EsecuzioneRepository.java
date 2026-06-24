package com.inspectra.backend.repository;

import com.inspectra.backend.model.Esecuzione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EsecuzioneRepository extends JpaRepository<Esecuzione, Long> {


    boolean existsByNumeroAndProva_ProvaIdAndPianoIndagine_PianoId(


            Integer numero,


            Long provaId,


            Long pianoId


    );


    boolean existsByNumeroAndProva_ProvaIdAndPianoIndagine_PianoIdAndEsecuzioneIdNot(


            Integer numero,


            Long provaId,


            Long pianoId,


            Long esecuzioneId


    );

    List<Esecuzione>
    findByPianoIndaginePianoIdAndIspezioneIsNull(
            Long pianoId
    );
}