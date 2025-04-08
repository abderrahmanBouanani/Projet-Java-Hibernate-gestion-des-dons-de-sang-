package test;

import dao.DonDao;
import dao.DonneurDao;
import dao.CentreDonDao;
import entities.Don;
import entities.Donneur;
import entities.CentreDon;
import java.util.Date;

public class TestDon {
    public static void main(String[] args) {
        // Initialisation des DAO
        DonDao donDao = new DonDao();
        DonneurDao donneurDao = new DonneurDao();
        CentreDonDao centreDonDao = new CentreDonDao();
        
        // Creation des entites
        Donneur donneur = new Donneur("AB+", new Date(), "Marie Martin", "marie@mail.com", "password");
        donneurDao.create(donneur);
        
        CentreDon centre = new CentreDon("Centre Ville", "456 Avenue Centrale");
        centreDonDao.create(centre);
        
        // Creation d'un don
        Don don = new Don(new Date(), "Salle 101", donneur, centre);
        donDao.create(don);
        
        // Verification
        System.out.println("Don enregistre :");
        System.out.println("Date: " + don.getDate());
        System.out.println("Donneur: " + don.getDonneur().getNom());
        System.out.println("Centre: " + don.getCentreDon().getNom());
    }
}