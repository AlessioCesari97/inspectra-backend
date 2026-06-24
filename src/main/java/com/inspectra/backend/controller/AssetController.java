package com.inspectra.backend.controller;


import com.inspectra.backend.dto.AssetDetailDTO;
import com.inspectra.backend.dto.AssetListDTO;

import com.inspectra.backend.model.Asset;

import com.inspectra.backend.repository.AssetRepository;

import com.inspectra.backend.service.AssetService;


import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;





@RestController
@RequestMapping("/assets")
@CrossOrigin("*")
public class AssetController {


    private final AssetService service;


    private final AssetRepository repository;





    public AssetController(

            AssetService service,

            AssetRepository repository
    ) {


        this.service = service;


        this.repository = repository;


    }







    // =========================
    // LISTA ASSET
    // =========================

    @GetMapping
    public List<AssetListDTO> getAll() {


        return service.getAllAssets();


    }










    // =========================
    // DETTAGLIO ASSET
    // =========================

    @GetMapping("/{assetId}")
    public AssetDetailDTO getDetail(


            @PathVariable Long assetId

    ) {


        return service.getAssetDetail(assetId);


    }













    // =========================
    // CREAZIONE ASSET
    // =========================

    @PostMapping
    public ResponseEntity<?> create(


            @Valid

            @RequestBody Asset asset

    ) {





        asset.setNome(

                asset.getNome().trim()

        );




        asset.setStrada(

                asset.getStrada().trim()

        );




        asset.setGestore(

                asset.getGestore().trim()

        );




        asset.setPosizione(

                asset.getPosizione().trim()

        );









        boolean exists =


                repository

                        .existsByNomeIgnoreCaseAndStradaIgnoreCase(


                                asset.getNome(),


                                asset.getStrada()

                        );









        if(exists) {


            return ResponseEntity

                    .status(HttpStatus.CONFLICT)

                    .body(

                            "Esiste già un viadotto con questo nome e questa strada"

                    );


        }









        Asset savedAsset =

                repository.save(asset);





        return ResponseEntity.ok(


                savedAsset

        );


    }














    // =========================
    // MODIFICA ASSET
    // =========================

    @PutMapping("/{assetId}")
    public ResponseEntity<?> update(



            @PathVariable Long assetId,



            @Valid

            @RequestBody Asset updatedAsset


    ) {






        Asset asset =


                repository.findById(assetId)


                        .orElse(null);







        if(asset == null) {


            return ResponseEntity

                    .status(HttpStatus.NOT_FOUND)

                    .body(

                            "Asset non trovato"

                    );


        }










        asset.setNome(

                updatedAsset.getNome().trim()

        );




        asset.setStrada(

                updatedAsset.getStrada().trim()

        );




        asset.setGestore(

                updatedAsset.getGestore().trim()

        );




        asset.setPosizione(

                updatedAsset.getPosizione().trim()

        );




        asset.setNote(

                updatedAsset.getNote()

        );









        Asset savedAsset =

                repository.save(asset);





        return ResponseEntity.ok(


                savedAsset

        );


    }















    // =========================
    // ELIMINA ASSET
    // =========================

    @DeleteMapping("/{assetId}")
    public ResponseEntity<?> delete(


            @PathVariable Long assetId

    ) {





        if(!repository.existsById(assetId)) {


            return ResponseEntity

                    .status(HttpStatus.NOT_FOUND)

                    .body(

                            "Asset non trovato"

                    );


        }






        repository.deleteById(assetId);




        return ResponseEntity.ok(

                "Asset eliminato correttamente"

        );


    }



}