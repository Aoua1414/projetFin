package com.aoua.medoc.repository;

import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RdvRepository extends JpaRepository <Rdv, Long> {

    // tous les rdv du jour dans notif
    List<Rdv> findByDateAndUser(LocalDate date, User user);

  // les rdv du user connecté



//    Rdv findById_rdv(Long id_user);

    @Query(value = "SELECT rdv.* FROM rdv WHERE rdv.id_rdv=:id_rdv" , nativeQuery = true)
    Rdv afficherparid (@Param("id_rdv") Long id_rdv);
//

    //recuperer rdv par user
    List<Rdv> findByUser(User user);
}
