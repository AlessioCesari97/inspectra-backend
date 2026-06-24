package com.inspectra.backend.repository;


import com.inspectra.backend.model.TipoElemento;

import org.springframework.data.jpa.repository.JpaRepository;



public interface TipoElementoRepository

        extends JpaRepository<TipoElemento, Long> {



    boolean existsByNomeIgnoreCase(

            String nome

    );



}