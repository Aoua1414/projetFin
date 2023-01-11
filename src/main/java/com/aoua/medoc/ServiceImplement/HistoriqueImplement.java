package com.aoua.medoc.ServiceImplement;


import com.aoua.medoc.Service.HistoriqueService;
import com.aoua.medoc.models.Historique;
import com.aoua.medoc.models.Pharmacien;
import com.aoua.medoc.repository.HistoriqueRepository;
import com.aoua.medoc.repository.PharmacienRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HistoriqueImplement implements HistoriqueService {
    private final HistoriqueRepository historiqueRepository;
    private final PharmacienRepository pharmacienRepository;

    public HistoriqueImplement(HistoriqueRepository historiqueRepository,
                               PharmacienRepository pharmacienRepository) {
        this.historiqueRepository = historiqueRepository;
        this.pharmacienRepository = pharmacienRepository;
    }

    @Override
    public Historique ajouter(Historique historique) {
        return historiqueRepository.save(historique);
    }

    @Override
    public List<Pharmacien> afficher() {
        return pharmacienRepository.findAll();
    }
}