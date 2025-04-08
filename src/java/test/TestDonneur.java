package test;

import dao.DonneurDao;
import dao.CentreDonDao;
import entities.Donneur;
import entities.CentreDon;
import java.util.Date;

public class TestDonneur {
    public static void main(String[] args) {
        // Initialisation
        DonneurDao donneurDao = new DonneurDao();
        CentreDonDao centreDonDao = new CentreDonDao();
        
        // Creation d'un centre de don
        CentreDon centre = new CentreDon("Centre Principal", "123 Rue des Dons");
        centreDonDao.create(centre);
        
        // Creation d'un donneur
        Donneur donneur = new Donneur("A+", new Date(), "Yassine Salihi", "salihi@mail.com", "password");
        donneurDao.create(donneur);
        
        // Recuperation et affichage
        Donneur d = donneurDao.findById(donneur.getId());
        System.out.println("Donneur cree : " + d.getNom() + " - Groupe : " + d.getGroupeSanguin());
        
        // Mise a jour
        d.setGroupeSanguin("O+");
        donneurDao.update(d);
        System.out.println("Groupe sanguin mis a jour : " + d.getGroupeSanguin());
    }
}