package com.aoua.medoc.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

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
    Historique historique;


}
