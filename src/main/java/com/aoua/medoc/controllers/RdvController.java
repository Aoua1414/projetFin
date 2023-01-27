package com.aoua.medoc.controllers;

import com.aoua.medoc.Service.RdvService;
import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(value = "http://localhost:8100",maxAge = 3600,allowCredentials = "true")
@RequestMapping("/rdv")
public class RdvController {
    public final RdvService rdvService;
    @Autowired
     RdvRepository rdvRepository;
    public RdvController(RdvService rdvService) {
        this.rdvService = rdvService;
    }
    //Afficher liste des rdv

    @GetMapping(value = "/liste")
//    @PreAuthorize("hasAuthority(USER)")
    public List<Rdv> afficher(){
        return rdvRepository.findAll();
    }
    //Ajouter rdv

    @PostMapping(value = "/ajouter")
     public String ajouter(@RequestBody Rdv rdv){
        try {
            rdvService.ajouter(rdv,rdv.getUser().getId());
            return "Ajouté avec succès";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    //Modification rdv
    @PutMapping(value = "/modifier/{id}")
//    @PreAuthorize("hasAuthority(USER)")
    public Rdv modifier(@PathVariable Long id, @RequestBody Rdv rdv){
//        rdvService.modifier(id, rdv);
        return rdvService.modifier(id, rdv );
    }

    //Suppression RDV
    @DeleteMapping(value = "/suprimer/{id}")
//    @PreAuthorize("hasAuthority(USER)")
    public String supprimer(@PathVariable long id){
        return rdvService.supprimer(id);
    }
}
