package com.aoua.medoc.models;

import javax.persistence.*;


@Entity
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne
    private User user;


}
