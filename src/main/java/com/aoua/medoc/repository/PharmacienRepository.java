package com.aoua.medoc.repository;

import com.aoua.medoc.models.Pharmacien;
import com.aoua.medoc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacienRepository extends JpaRepository <Pharmacien, Long> {

    Pharmacien findByUser(User user);
}
