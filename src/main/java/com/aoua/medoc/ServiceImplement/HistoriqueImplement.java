package com.aoua.medoc.ServiceImplement;


import com.aoua.medoc.Service.HistoriqueService;
import com.aoua.medoc.models.Historique;
import com.aoua.medoc.repository.HistoriqueRepository;
import org.springframework.stereotype.Service;

@Service

public class HistoriqueImplement implements HistoriqueService {
    private final HistoriqueRepository historiqueRepository;

    public HistoriqueImplement(HistoriqueRepository historiqueRepository) {
        this.historiqueRepository = historiqueRepository;
    }

    @Override
    public Historique ajouter(Historique historique) {
        return historiqueRepository.save(historique);
    }
}
