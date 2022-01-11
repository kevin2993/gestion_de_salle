package com.example.gestionSalle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.sound.midi.Sequence;

import static javax.persistence.GenerationType.SEQUENCE;
//@JsonIgnoreProperties(value={"id"})
@Entity(name ="Salle" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salle {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "salle_sequence"
    )
    private Integer id;
    @Column(
            name = "nom",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String nom;
    private Integer capacitMax;


}
