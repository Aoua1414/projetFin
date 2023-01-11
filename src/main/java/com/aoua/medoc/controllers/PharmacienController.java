package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.PharmacienService;
import com.aoua.medoc.models.Pharmacien;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pharmacien")
@RestController
public class PharmacienController {
    public final PharmacienService pharmacienService;

    public PharmacienController(PharmacienService pharmacienService) {
        this.pharmacienService = pharmacienService;
    }
    //Afficher pharmaciens
    @GetMapping(value = "/liste")
    @PreAuthorize("hasAuthority('USER,ADMIN')")

    public List<Pharmacien> afficher(){
        return pharmacienService.afficher();
    }
    //Ajouter pharmacien
    @PostMapping("/ajouter")
    @PreAuthorize("hasAuthority(ADMIN)")
    public Object ajouter(Pharmacien pharmacien){
        try{
            pharmacienService.ajouter(pharmacien);
            return "Ajouté avec succès";
        }catch (Exception e){
            return "Existe deja";
        }
    }
    // Modifier pharmacien
    @PutMapping(value = "/modifier/{id}")
    @PreAuthorize("hasAutority(ADMIN)")
    public String modifier(@PathVariable Long id, Pharmacien pharmacien){
        pharmacienService.modifier(id, pharmacien);
        return "modifié avec succès";
    }
    //supprimer pharmacien
    @DeleteMapping(value = "/supprimer/{id}")
    @PreAuthorize("hasAuthority(ADMIN)")
    public String supprimer(@PathVariable long id){
        return pharmacienService.supprimer(id);
    }

}
