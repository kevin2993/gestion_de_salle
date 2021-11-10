package com.example.gestionSalle.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    private Integer id;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private Date creneau;
    private String nomSalle;
    private String typeReunion;

    public Reservation(){

    }

    public Reservation(Integer id, Date creneau, String nomSalle, String typeReunion) {
        this.id = id;
        this.creneau = creneau;
        this.nomSalle = nomSalle;
        this.typeReunion = typeReunion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreneau() {
        return creneau;
    }

    public void setCreneau(Date creneau) {
        this.creneau = creneau;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public String getTypeReunion() {
        return typeReunion;
    }

    public void setTypeReunion(String typeReunion) {
        this.typeReunion = typeReunion;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", creneau='" + creneau + '\'' +
                ", nomSalle='" + nomSalle + '\'' +
                ", typeReunion='" + typeReunion + '\'' +
                '}';
    }
}
