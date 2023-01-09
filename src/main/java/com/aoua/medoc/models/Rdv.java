package com.aoua.medoc.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

public class Rdv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rdv;
    private String service_medical;
    private String motif;
    private Date date_rdv;
    private Time heure_rdv;

    @ManyToOne
    Historique historique;

}
