package com.example.gestionSalle.controller;

import com.example.gestionSalle.dao.EquipementDao;
import com.example.gestionSalle.dao.ReservationDao;
import com.example.gestionSalle.dao.SalleDao;
import com.example.gestionSalle.exceptions.salleIntrouvableException;
import com.example.gestionSalle.model.Equipement;
import com.example.gestionSalle.model.Reservation;
import com.example.gestionSalle.model.Salle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class reunionController {

    @Autowired
    private SalleDao salleDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private EquipementDao equipementDao;


    //recuperer la liste de toutes les salles
    @RequestMapping(value = "/salles", method = RequestMethod.GET)
    public List<Salle> getAllSalle() {
        return salleDao.findAll();
    }

    //liste de toutes les reservations
    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public List<Reservation> getAllReservation() {
        return reservationDao.findAll();
    }

    //liste de toutes les equipements
    @RequestMapping(value = "/equipements", method = RequestMethod.GET)
    public List<Equipement> getAllEquipement() {
        return equipementDao.findAll();
    }

    //liste equipement by idsalle
    @RequestMapping(value = "equipement/{idSalle}", method = RequestMethod.GET)
    public List<Equipement> getEquipementSalle(@PathVariable int idSalle) {
        return equipementDao.chercherEquipementSalle(idSalle);
    }


    //liste des salles pouvant contenir {personne}
    @RequestMapping(value = "salle/{nbperson}", method = RequestMethod.GET)
    public List<Salle> getSalleNbPerson(@PathVariable int nbperson) throws salleIntrouvableException {
        //return salleDao.chercherSalleDispo(nbperson);
        List<Salle> salles = salleDao.findAll();
        List<Salle> salleP = new ArrayList<Salle>();
        for (Salle salle : salles) {
            if ((salle.getCapacitMax() * 0.7) >= nbperson)
                salleP.add(salle);
        }
        if (salleP.isEmpty()) throw new salleIntrouvableException("Aucune salle n'est en capacité de contenir "+nbperson+" personne");
        return salleP;
    }

    //liste de salle reservé qui sont à present disponible à x heure
    @RequestMapping(value = "salleR-dispo/{time}", method = RequestMethod.GET)
    public List<Salle> getSalleNbPerson(@PathVariable @DateTimeFormat(pattern = "HH:mm:ss") Date time) throws salleIntrouvableException {
        //ajout d'une heure sur l'heure de disponibilité


        List<Reservation> reservations = reservationDao.findAll();
        List<Salle> salles = salleDao.findAll();
        List<Salle> salleP = new ArrayList<Salle>();
        for (Reservation reservation : reservations) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(reservation.getCreneau());
            cal.add(Calendar.HOUR,1);
            Date time1 =cal.getTime();
            if (time.after(reservation.getCreneau()) && time.compareTo(time1)!=0)
                for(Salle salle: salles)
                    if(reservation.getNomSalle().compareTo(salle.getNom())==0)
                        salleP.add(salle);
        }
        if (salleP.isEmpty()) throw new salleIntrouvableException("Aucune salle dans la liste des résevations n'est disponible");
        return salleP;
    }


    @RequestMapping(value = "salle-reunion/{typeReunion}", method = RequestMethod.GET)
    public List<Salle> getSalleReunion(@PathVariable String typeReunion) throws salleIntrouvableException {
        int k=0;
        List<Salle> salles = salleDao.findAll();
        List<Salle> salleP = new ArrayList<Salle>();
        List<Equipement>equipements= new ArrayList<Equipement>();
        // for(Salle salle:salles){
        if (typeReunion.equals("VC")) {
            for(Salle salle:salles){
                equipements=equipementDao.chercherEquipementSalle(salle.getId());
                for(Equipement equipement:equipements){
                    if(equipement.getNom().equals("ecran") || equipement.getNom().equals("pieuvre") || equipement.getNom().equals("webcam") )
                         k++;
                }
                if(k==3)
                    salleP.add(salle);
                k=0;
            }
        }
        if (typeReunion.equals("SPEC")) {
            for(Salle salle:salles){
                equipements=equipementDao.chercherEquipementSalle(salle.getId());
                for(Equipement equipement:equipements){
                    if(equipement.getNom().equals("tableau"))
                        k++;
                }
                if(k==1)
                    salleP.add(salle);
                k=0;
            }
        }
        if (typeReunion.equals("RS")) {
            salleP=getSalleNbPerson(4);
            return salleP;
        }
        if (typeReunion.equals("RC")) {
            for(Salle salle:salles){
                equipements=equipementDao.chercherEquipementSalle(salle.getId());
                for(Equipement equipement:equipements){
                    if(equipement.getNom().equals("tableau") || equipement.getNom().equals("ecran") || equipement.getNom().equals("pieuvre"))
                        k++;
                }
                if(k==3)
                    salleP.add(salle);
                k=0;
            }
        }

        if (salleP.isEmpty()) throw new salleIntrouvableException("Aucune salle ne répond au critère de ce type de réunion");
        return salleP;
    }

    /*//recuperer la liste des salles dont la capacité à 70% contient les personnes
    @RequestMapping(value = "salles/{nbperson", method = RequestMethod.GET)
    public List<Salle> getSalleByNbPerson(@PathVariable int nbperson){
        return salleDao.getSalleParNbPersonne(nbperson);
    }*/
}
