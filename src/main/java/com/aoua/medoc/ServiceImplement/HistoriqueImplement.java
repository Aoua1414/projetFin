package com.aoua.medoc.ServiceImplement;


import com.aoua.medoc.Service.HistoriqueService;
import com.aoua.medoc.models.Historique;
import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.repository.HistoriqueRepository;
import com.aoua.medoc.repository.PharmacienRepository;
import com.aoua.medoc.repository.RdvRepository;
import com.aoua.medoc.repository.TraitementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HistoriqueImplement implements HistoriqueService {
    private final HistoriqueRepository historiqueRepository;
    private final PharmacienRepository pharmacienRepository;
    private final RdvRepository rdvRepository;
    private final TraitementRepository traitementRepository;

    public HistoriqueImplement(HistoriqueRepository historiqueRepository,
                               PharmacienRepository pharmacienRepository,
                               RdvRepository rdvRepository,
                               TraitementRepository traitementRepository) {
        this.historiqueRepository = historiqueRepository;
        this.pharmacienRepository = pharmacienRepository;
        this.rdvRepository = rdvRepository;
        this.traitementRepository = traitementRepository;
    }

    @Override
    public Historique ajouter(Historique historique) {
        return historiqueRepository.save(historique);
    }

    @Override
    public List<Traitement> listerTraitement() {
        return traitementRepository.findAll();
    }

    @Override
    public List<Rdv> listerRdv() {
        return rdvRepository.findAll();
    }


}