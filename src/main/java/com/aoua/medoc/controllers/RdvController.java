package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.RdvService;
import com.aoua.medoc.models.Rdv;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rdv")
public class RdvController {
    public final RdvService rdvService;

    public RdvController(RdvService rdvService) {
        this.rdvService = rdvService;
    }
    //Afficher liste des rdv

    @GetMapping(value = "/liste")
    @PreAuthorize("hasAuthority(USER)")

    public List<Rdv> afficher(){
        return rdvService.afficher();
    }
    //Ajouter rdv

    @PostMapping(value = "/ajouter")
    @PreAuthorize("hasAuthority(USER)")
     public Object ajouter(Rdv rdv){
        try {
            rdvService.ajouter(rdv);
            return "Ajouté avec succès";
        }catch (Exception e){
            return "Existe deja";
        }
    }

    //Modification rdv
    @PutMapping(value = "/modifier/{motif}")
    @PreAuthorize("hasAuthority(USER)")
    public String modifier(@PathVariable Long id, Rdv rdv){
        rdvService.modifier(id, rdv);
        return "modifié avec succès ";
    }

    //Suppression RDV
    @DeleteMapping(value = "/suprimer/{id}")
    @PreAuthorize("hasAuthority(USER)")
    public String supprimer(@PathVariable long id){
        return rdvService.supprimer(id);
    }
}
