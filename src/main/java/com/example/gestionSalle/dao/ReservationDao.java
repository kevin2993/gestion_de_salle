package com.example.gestionSalle.dao;

import com.example.gestionSalle.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAll();
}
