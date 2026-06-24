package com.inspectra.backend.repository;


import com.inspectra.backend.model.Elemento;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ElementoRepository
        extends JpaRepository<Elemento, Long> {



    boolean existsByAssetAssetIdAndCodiceIgnoreCase(

            Long assetId,

            String codice

    );


}