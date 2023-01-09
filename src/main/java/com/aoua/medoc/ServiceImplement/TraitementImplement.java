package com.aoua.medoc.ServiceImplement;


import com.aoua.medoc.Service.TraitementService;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.repository.TraitementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class TraitementImplement  implements TraitementService {

    private final TraitementRepository traitementRepository;

    @Override
    public Traitement ajouter(Traitement traitement) {
        return traitementRepository.save(traitement);
            }

    @Override
    public Traitement modifier(Long id, Traitement traitement) {

        return traitementRepository.findById(id)
                .map(traitement1 -> {
                    traitement1.setDuree_traitement(traitement.getDuree_traitement());
                    traitement1.setDate_fin(traitement.getDate_fin());
                    traitement1.setIntervalle(traitement.getIntervalle());
                    traitement1.setDate_debut(traitement.getDate_debut());
                    traitement1.setNom_medoc(traitement.getNom_medoc());
                    traitement1.setNbrePillule(traitement.getNbrePillule());
                    traitement1.setPremiere_prise(traitement.getPremiere_prise());
                    traitement1.setFois_parjour(traitement.getFois_parjour());
                    return traitementRepository.save(traitement1);
                }).orElseThrow(()->new RuntimeException("Traitement introuvable"));
                }

    @Override
    public String suupprimer(Long id) {
        traitementRepository.deleteById(id);
        return ("supprime avec succes");
    }




    @Override
    public List<Traitement> afficher() {
        return traitementRepository.findAll();
    }
}



