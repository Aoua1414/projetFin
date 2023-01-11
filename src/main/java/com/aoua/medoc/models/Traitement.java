package com.aoua.medoc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_traitement;
    private String nom_medoc;
    private String duree_traitement;
    private String nbrePillule;
    private String fois_parjour;
    private Date date_debut;
    private Date date_fin;
    private Time premiere_prise;
    private int intervalle;

    @ManyToOne
   private Historique historique;

    @ManyToOne
    private User user;


}
