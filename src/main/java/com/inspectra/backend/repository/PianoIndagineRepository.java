package com.inspectra.backend.repository;

import com.inspectra.backend.model.PianoIndagine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PianoIndagineRepository extends JpaRepository<PianoIndagine, Long> {


    boolean existsByCodicePianoIgnoreCase(


            String codicePiano


    );
}