package com.inspectra.backend.repository;


import com.inspectra.backend.model.Asset;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AssetRepository
        extends JpaRepository<Asset,Long> {



    boolean existsByNomeIgnoreCaseAndStradaIgnoreCase(

            String nome,

            String strada
    );


}