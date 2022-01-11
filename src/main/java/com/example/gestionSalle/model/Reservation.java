package com.example.gestionSalle.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    private Integer id;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private Date creneau;
    private String nomSalle;
    private String typeReunion;


}
