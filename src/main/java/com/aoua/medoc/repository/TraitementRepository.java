package com.aoua.medoc.repository;

import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraitementRepository extends JpaRepository <Traitement, Long> {

    List<Traitement> findByUser(User user);
}
