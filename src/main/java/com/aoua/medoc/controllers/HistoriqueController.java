package com.aoua.medoc.controllers;


import com.aoua.medoc.Service.HistoriqueService;
import com.aoua.medoc.models.Historique;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historique")

public class HistoriqueController {
    public final HistoriqueService historiqueService;


    public HistoriqueController(HistoriqueService historiqueService) {
        this.historiqueService = historiqueService;
    }

    //Afficher historique
    @GetMapping("/afficher")

    public List<Historique> afficher(){
        return historiqueService.
    }
}
