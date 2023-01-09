package com.aoua.medoc.ServiceImplement;

import com.aoua.medoc.Service.RdvService;
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
    public Rdv ajouter(Rdv rdv) {

        return rdvRepository.save(rdv);

    }

    @Override
    public Rdv modifier(Long id, Rdv rdv) {
        return rdvRepository.findById(id)
                .map(rdv1 -> {
                    rdv1.setHeure_rdv(rdv.getHeure_rdv());
                    rdv1.setHeure_rdv(rdv.getHeure_rdv());
                    rdv1.setMotif(rdv.getMotif());
                    rdv1.setService_medical(rdv.getService_medical());
                    return rdvRepository.save(rdv1);
                }).orElseThrow(() -> new RuntimeException("Rendez-vous introuvable"));
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

