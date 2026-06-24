package com.inspectra.backend.repository;


import com.inspectra.backend.model.Prova;


import org.springframework.data.jpa.repository.JpaRepository;




public interface ProvaRepository

        extends JpaRepository<Prova, Long> {




    boolean existsByNomeProvaIgnoreCase(

            String nomeProva

    );





    boolean existsBySiglaIgnoreCase(

            String sigla

    );



}