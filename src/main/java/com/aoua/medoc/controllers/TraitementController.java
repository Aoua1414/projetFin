package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.TraitementService;
import com.aoua.medoc.models.Traitement;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TraitementController {
    public final TraitementService traitementService;


    public TraitementController(TraitementService traitementService) {
        this.traitementService = traitementService;
    }

    //Afficher liste des traitements
    @GetMapping(value = "/liste_traitement")

    public List<Traitement> afficher() {
        return traitementService.afficher();
    }

    @PostMapping(value = "/traitement")
    @PostAuthorize("hasAuthority(USER)")

    public Object ajouter(Traitement traitement) {
        try {
            traitementService.ajouter(traitement);
            return "Ajouté avec succès";

        } catch (Exception e) {

            return "existe deja";
        }
    }

    // Modification d'un traitement
    @PutMapping(value = "/modifier-traitement/{nom_medoc}")
    @PostAuthorize("hasAuthority('USER')")

    public String modifier(@PathVariable Long id , Traitement traitement) {
          traitementService.modifier(id, traitement);
          return "modifié avec succès ";
    }



    //suppression d'un traitement
    @DeleteMapping(value = "/supprimer_traitement/{id}")
    @PostAuthorize("hasAuthority('USER')")
    public String supprimer(@PathVariable long id){
        return traitementService.suupprimer(id);
    }

}