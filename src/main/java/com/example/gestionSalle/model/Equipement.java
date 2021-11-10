package com.example.gestionSalle.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipement {
    @Id
    private int id;
    private int idSalle;
    private String nom;

    public Equipement(int id, int idSalle, String nom) {
        this.id = id;
        this.idSalle = idSalle;
        this.nom = nom;
    }

    public Equipement(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Equipement{" +
                "id=" + id +
                ", idSalle=" + idSalle +
                ", nom='" + nom + '\'' +
                '}';
    }
}
