package test;

import dao.DonneurDao;
import dao.DonDao;
import entities.Donneur;
import entities.Don;
import java.util.Date;

public class TestRelations {
    public static void main(String[] args) {
        DonneurDao donneurDao = new DonneurDao();
        DonDao donDao = new DonDao();
        
        // Creation d'un donneur avec plusieurs dons
        Donneur donneur = new Donneur("B-", new Date(), "Hatim Koubri", "koubri@mail.com", "password");
        donneurDao.create(donneur);
        
        // Creation de plusieurs dons
        Don don1 = new Don(new Date(), "Salle A", donneur, null);
        Don don2 = new Don(new Date(), "Salle B", donneur, null);
        
        donDao.create(don1);
        donDao.create(don2);
        
        // Recuperation et verification des relations
        Donneur d = donneurDao.findById(donneur.getId());
        System.out.println("Dons du donneur " + d.getNom() + ":");
        for(Don don : d.getDons()) {
            System.out.println("- " + don.getDate() + " a " + don.getLieu());
        }
    }
}