package com.aoua.medoc.Service;


import com.aoua.medoc.models.Messages;
import com.aoua.medoc.models.Traitement;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface TraitementService{

    Messages ajouter(Traitement traitement, long id);

    Traitement modifier(Long id, Traitement traitement);

    String suupprimer(Long id);

    List<Traitement> afficher();

    List<Traitement> listerParUser(Long Id);





   // List<Traitement> flitreDate(LocalDate datedebut);




}
