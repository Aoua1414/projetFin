package com.aoua.medoc.repository;

import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RdvRepository extends JpaRepository <Rdv, Long> {

    List<Rdv> findByDateAndUser(LocalDate date, User user);
}
