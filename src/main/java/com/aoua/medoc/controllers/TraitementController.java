package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.TraitementService;
import com.aoua.medoc.models.Traitement;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traitement")
public class TraitementController {
    public final TraitementService traitementService;


    public TraitementController(TraitementService traitementService) {
        this.traitementService = traitementService;
    }

    //Afficher liste des traitements
    @GetMapping(value = "/liste")

    public List<Traitement> afficher() {
        return traitementService.afficher();
    }
//Ajouter traitement
    @PostMapping(value = "/ajouter")
    @PreAuthorize("hasAuthority(USER)")

    public Object ajouter(Traitement traitement) {
        try {
            traitementService.ajouter(traitement);
            return "Ajouté avec succès";

        } catch (Exception e) {

            return "existe deja";
        }
    }

    // Modification d'un traitement
    @PutMapping(value = "/modifier/{nom_medoc}")
    @PreAuthorize("hasAuthority('USER')")

    public String modifier(@PathVariable Long id , Traitement traitement) {
          traitementService.modifier(id, traitement);
          return "modifié avec succès ";
    }



    //suppression d'un traitement
    @DeleteMapping(value = "/supprimer/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String supprimer(@PathVariable long id){
        return traitementService.suupprimer(id);
    }

}