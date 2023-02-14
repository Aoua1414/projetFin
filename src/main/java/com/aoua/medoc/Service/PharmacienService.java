package com.aoua.medoc.Service;

import com.aoua.medoc.models.Pharmacien;
import com.aoua.medoc.models.User;

import java.util.List;

public interface PharmacienService {

    Pharmacien ajouter(Pharmacien pharmacien);

    Pharmacien modifier(Long id, Pharmacien pharmacien );

    String supprimer(Long id);

    List<Pharmacien> afficher();
}
