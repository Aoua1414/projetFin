package com.aoua.medoc.Service;


import com.aoua.medoc.models.Traitement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TraitementService{

    Traitement ajouter(Traitement traitement);

    Traitement modifier(Long id, Traitement traitement);

    String suupprimer(Long id);

    List<Traitement> afficher();




}
