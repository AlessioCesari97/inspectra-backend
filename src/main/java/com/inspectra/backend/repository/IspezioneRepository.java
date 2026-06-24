package com.inspectra.backend.repository;

import com.inspectra.backend.model.Ispezione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IspezioneRepository extends JpaRepository<Ispezione, Long> {
}