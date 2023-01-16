package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.TraitementService;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.repository.TraitementRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/traitement")
public class TraitementController {
    public final TraitementService traitementService;
    private final TraitementRepository traitementRepository;


    public TraitementController(TraitementService traitementService,
                                TraitementRepository traitementRepository) {
        this.traitementService = traitementService;
        this.traitementRepository = traitementRepository;
    }

    //Afficher liste des traitements
    @GetMapping(value = "/liste")

    public List<Traitement> afficher() {
        return traitementService.afficher();
    }
//Ajouter traitement
    @PostMapping(value = "/ajouter")
    public Object ajouter(@RequestBody Traitement traitement) {
//        try {

            return  traitementService.ajouter(traitement);

//        } catch (Exception e) {
//
//            return e.getMessage();
//        }
    }

    // Modification d'un traitement
    @PutMapping(value = "/modifier/{nom_medoc}")
    @PreAuthorize("hasAuthority('USER')")

    public String modifier(@PathVariable Long id , @RequestBody Traitement traitement) {
          traitementService.modifier(id, traitement);
          return "modifié avec succès ";
    }

    @GetMapping(value = "/filtrertraitementParDate")


    public String filtrerParDate(@RequestParam (value = "date1") LocalDate date1, @RequestParam(value = "date2") LocalDate date2) {

        Date today = new Date();
       Traitement trrt = (Traitement) traitementService.afficher();
       if(trrt.getDate_debut().isBefore(date1) && trrt.getDate_fin().isAfter(date2)){
           return trrt.getNom_medoc();
       }
       else{
       }
        return "modifié avec succès ";
    }

    //suppression d'un traitement
    @DeleteMapping(value = "/supprimer/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String supprimer(@PathVariable long id){
        return traitementService.suupprimer(id);
    }

}