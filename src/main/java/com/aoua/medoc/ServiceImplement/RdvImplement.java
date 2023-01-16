package com.aoua.medoc.ServiceImplement;

import com.aoua.medoc.Service.RdvService;
import com.aoua.medoc.models.Messages;
import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.repository.RdvRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class RdvImplement implements RdvService {
    private final RdvRepository rdvRepository;

    @Override
    public String ajouter(Rdv rdv) {

        rdvRepository.save(rdv);
        return "ENREGISTRE AVEC SUCCES";

    }

    @Override
    public Rdv modifier(Long id, Rdv rdv) {
        return rdvRepository.findById(id)
                .map(rdv1 -> {
//                    rdv1.setHeure_rdv(rdv.getHeure_rdv());
                    rdv1.setHeure_rdv(rdv.getHeure_rdv());
                    rdv1.setDate_rdv(rdv.getDate_rdv());
                    rdv1.setMotif(rdv.getMotif());
                    rdv1.setService_medical(rdv.getService_medical());
                    System.out.println(rdv1);
                    return rdvRepository.save(rdv1);
                }).orElseThrow(() -> new RuntimeException(String.valueOf(Messages.set("Rendez-vous introuvable",true))));
    }



    @Override
    public String supprimer(Long id) {
        rdvRepository.deleteById(id);
        return ("Supprime avec succes");
    }

    @Override
    public List<Rdv> afficher() {
        return rdvRepository.findAll();
    }


}

