package com.aoua.medoc.Service;

import com.aoua.medoc.models.Historique;
import com.aoua.medoc.models.Pharmacien;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


public interface HistoriqueService {

    Historique ajouter(Historique historique);

   List<Pharmacien> afficher();
}
