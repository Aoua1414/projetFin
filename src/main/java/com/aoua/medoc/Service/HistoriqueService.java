package com.aoua.medoc.Service;

import com.aoua.medoc.models.Historique;
import com.aoua.medoc.models.Pharmacien;
import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.models.Traitement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


public interface HistoriqueService {

    Historique ajouter(Historique historique);

   List<Traitement> listerTraitement();

   List<Rdv>listerRdv();
}
