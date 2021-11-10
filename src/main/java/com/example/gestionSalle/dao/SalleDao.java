package com.example.gestionSalle.dao;

import com.example.gestionSalle.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleDao extends JpaRepository<Salle,Integer> {
    
    List<Salle> findAll();
    /*@Query("SELECT s FROM Salle s where s.capacitMax > :nbPerson ")
    List<Salle> chercherSalleDispo(@Param("nbPerson") int nbPerson);*/

}
