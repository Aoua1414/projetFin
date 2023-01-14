package com.aoua.medoc.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    private LocalDate date_debut;
    private LocalDate date_fin;
    private LocalTime premiere_prise;
    private LocalTime intervalle;
    private Long duree_traitement;
    private String nbrePillule;

    @ManyToOne
    private User user;


}
