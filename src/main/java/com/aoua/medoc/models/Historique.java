package com.aoua.medoc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_histo;
    private Date date_histo;
    private String description;





}
