package com.example.gestionSalle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.sound.midi.Sequence;

import static javax.persistence.GenerationType.SEQUENCE;
//@JsonIgnoreProperties(value={"id"})
@Entity(name ="Salle" )
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

    public Salle(){}

    public Salle(int id, String nom, int capacitMax, boolean dispo) {
        this.id = id;
        this.nom = nom;
        this.capacitMax = capacitMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacitMax() {
        return capacitMax;
    }

    public void setCapacitMax(int capacitMax) {
        this.capacitMax = capacitMax;
    }



    @Override
    public String toString() {
        return "Salle{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", capacitMax=" + capacitMax +
                '}';
    }
}
