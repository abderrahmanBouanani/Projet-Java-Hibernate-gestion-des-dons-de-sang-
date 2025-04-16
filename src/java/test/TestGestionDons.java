package test;

import dao.AdminDao;
import dao.CentreDonDao;
import dao.DonDao;
import dao.DonneurDao;
import entities.Admin;
import entities.CentreDon;
import entities.Don;
import entities.Donneur;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class TestGestionDons {

    public static void main(String[] args) throws ParseException {
        
            
            // Initialisation des DAOs
            DonneurDao donneurDao = new DonneurDao();
            CentreDonDao centreDao = new CentreDonDao();
            DonDao donDao = new DonDao();
            AdminDao adminDao = new AdminDao();

            // 1. Création d'un administrateur marocain
            Admin admin = new Admin("BOUANANI Abderrahman", "bouanani@gmail.com", "admin123", "ADM-MA-001");
            adminDao.create(admin);
            System.out.println("[SUCCESS] Admin créé: " + admin.getName() + " (Matricule: " + admin.getMatricule() + ")");

            // 2. Création de donneurs marocains avec différents groupes sanguins
            Donneur donneur1 = new Donneur("EL FILALI Karim", "karim@mail.ma", "pass123", "A+");
            Donneur donneur2 = new Donneur("BENNANI Fatima", "fatima@mail.ma", "pass123", "B+");
            Donneur donneur3 = new Donneur("ALAOUI Ahmed", "ahmed@mail.ma", "pass123", "O+");
            
            donneurDao.create(donneur1);
            donneurDao.create(donneur2);
            donneurDao.create(donneur3);
            
            System.out.println("\n[SUCCESS] Donneurs marocains créés:");
            System.out.println("- " + donneur1.getName() + " (Groupe: " + donneur1.getGroupeSanguin() + ")");
            System.out.println("- " + donneur2.getName() + " (Groupe: " + donneur2.getGroupeSanguin() + ")");
            System.out.println("- " + donneur3.getName() + " (Groupe: " + donneur3.getGroupeSanguin() + ")");

            // 3. Création de centres de don au Maroc
            CentreDon centre1 = new CentreDon("Centre de transfusion sanguine, Rue Ibn Sina, Rabat");
            CentreDon centre2 = new CentreDon("Centre régional de transfusion sanguine, Avenue Hassan II, Casablanca");
            
            centreDao.create(centre1);
            centreDao.create(centre2);
            
            System.out.println("\n[SUCCESS] Centres de don marocains créés:");
            System.out.println("- " + centre1.getAdresseCentre());
            System.out.println("- " + centre2.getAdresseCentre());

            // 4. Enregistrement des dons avec différentes dates
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateRecent = sdf.parse("15/01/2024");
            Date dateAncienne = sdf.parse("10/06/2023");
            
            Don don1 = new Don(dateRecent, "Salle A", donneur1, centre1);
            Don don2 = new Don(dateAncienne, "Salle B", donneur2, centre1);
            Don don3 = new Don(dateRecent, "Salle C", donneur3, centre2);
            
            donDao.create(don1);
            donDao.create(don2);
            donDao.create(don3);
            
            System.out.println("\n[SUCCESS] Dons enregistrés:");
            System.out.println("- Don de " + don1.getDonneur().getName() + " le " + sdf.format(don1.getId().getDateDon()));
            System.out.println("- Don de " + don2.getDonneur().getName() + " le " + sdf.format(don2.getId().getDateDon()));
            System.out.println("- Don de " + don3.getDonneur().getName() + " le " + sdf.format(don3.getId().getDateDon()));

            // 5. Test des nouvelles méthodes
            System.out.println("\n=== TESTS DES NOUVELLES METHODES ===");
            
            // a) Test findByBloodGroup
            System.out.println("\nTest findByBloodGroup ('B+'):");
            List<Donneur> donneursBPlus = donneurDao.findByBloodGroup("B+");
            if(donneursBPlus.isEmpty()) {
                System.out.println("Aucun donneur trouvé avec ce groupe sanguin");
            } else {
                donneursBPlus.forEach(d -> 
                    System.out.println("- " + d.getName() + " (" + d.getGroupeSanguin() + ")")
                );
            }
            
            // b) Test findDonorsWithDonationsAfterDate
            Date dateTest = sdf.parse("01/01/2024");
            System.out.println("\nTest findDonorsWithDonationsAfterDate (après le 01/01/2024):");
            List<Donneur> donneursRecents = donneurDao.findDonorsWithDonationsAfterDate(dateTest);
            if(donneursRecents.isEmpty()) {
                System.out.println("Aucun donneur trouvé avec des dons après cette date");
            } else {
                donneursRecents.forEach(d -> 
                    System.out.println("- " + d.getName() + " a fait un don récent")
                );
            }
            
            // c) Test findDonationCentersByDonor
            System.out.println("\nTest findDonationCentersByDonor (pour " + donneur1.getName() + "):");
            List<CentreDon> centresFrequentes = donneurDao.findDonationCentersByDonor(donneur1);
            if(centresFrequentes.isEmpty()) {
                System.out.println("Ce donneur n'a fréquenté aucun centre");
            } else {
                centresFrequentes.forEach(c -> 
                    System.out.println("- " + c.getAdresseCentre())
                );
            }
            
            
            System.out.println("\n[SUCCESS] Tous les tests ont été exécutés avec succès!");
            
        
    }
}