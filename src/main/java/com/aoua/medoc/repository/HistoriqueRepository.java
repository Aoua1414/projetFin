package com.aoua.medoc.repository;

import com.aoua.medoc.models.Historique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriqueRepository extends JpaRepository <Historique, Long> {
}
