package com.aoua.medoc.Service;

import com.aoua.medoc.models.Rdv;

import java.util.List;

public interface RdvService {

    String ajouter (Rdv rdv);
    Rdv modifier (Long id, Rdv rdv);

    String supprimer (Long id);

    List<Rdv> afficher();
}
