package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.TraitementService;
import com.aoua.medoc.ServiceImplement.NotificationImplement;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.models.User;
import com.aoua.medoc.repository.TraitementRepository;
import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/traitement")
public class TraitementController {
    @Autowired
    public final TraitementService traitementService;

    @Autowired
    private final TraitementRepository traitementRepository;


    public TraitementController(TraitementService traitementService,
                                TraitementRepository traitementRepository) {
        this.traitementService = traitementService;
        this.traitementRepository = traitementRepository;
    }

    //Afficher liste des traitements
    @GetMapping(value = "/jour/{id}")

    public List<Traitement> afficher(@PathVariable Long id) {


        Traitement traitementList =  traitementRepository.findById(id).get();
        LocalDate lt
                = LocalDate.now();
        List<Traitement> traitedujour = new ArrayList<>();
       if(traitementList.getDate_debut().equals(lt) || traitementList.getDate_debut().isBefore(lt) && traitementList.getDate_fin().isAfter(lt)){
           traitedujour.add(traitementList);
       }
        return traitedujour;
    }


    @GetMapping(value = "/liste")

    public List<Traitement> afficherTraitementJour() {
        return traitementService.afficher();
    }


     //Ajouter traitement
    @PostMapping(value = "/ajouter/{id}")
    public Object ajouter(@RequestBody Traitement traitement, @PathVariable("id") User user) {
//        try {
       System.out.println("----- "+traitement.getIntervalle());
        NotificationImplement.sendNotification();
            return  traitementService.ajouter(traitement,user.getId());

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

    /*
    @GetMapping(value = "/filtrertraitementParDate")
    public List<Traitement> filtrerParDate(@RequestParam  LocalDate date1) {


        return traitementService(date1);*/
       /* Date today = new Date();

        System.out.println("TYUI"+date1);
       Traitement trrt = (Traitement) traitementService.afficher();
       if(trrt.getDate_debut().isBefore(date1) ){
           return trrt.getNom_medoc();
       }
       else{
       }*/

   // }

    //suppression d'un traitement
    @DeleteMapping(value = "/supprimer/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public String supprimer(@PathVariable long id){
        return traitementService.suupprimer(id);
    }

}