package test;

import entities.Admin;
import entities.CentreDon;
import entities.Don;
import entities.Donneur;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;

/**
 * Classe de test pour le systeme de gestion des dons de sang
 */
public class TestGestionDons {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Creation d'un admin
            Admin admin = new Admin("ADM-789", "Admin Système", "admin@centresang.com", "adminPass");
            session.save(admin);
            System.out.println("✅ Admin cree : " + admin.getMatricule());

            // Creation d'un donneur
            Donneur donneur = new Donneur("O+", new Date(), "Bouanani Abderrahman", "bouanani@mail.com", "bn123");
            session.save(donneur);
            System.out.println("✅ Donneur cree : " + donneur.getNom());

            // Creation d'un centre de don
            CentreDon centre = new CentreDon("Centre Principal", "123 Rue des Dons, Ville");
            session.save(centre);
            System.out.println("✅ Centre cree : " + centre.getNom());

            // Creation d'un don
            Don don = new Don();
            don.setDate(new Date());
            don.setLieu("Salle 1");
            don.setDonneur(donneur);
            don.setCentreDon(centre);
            session.save(don);
            System.out.println("✅ Don enregistre le " + don.getDate());

            tx.commit();

            // Requetes de verification
            System.out.println("\n--- VÉRIFICATIONS ---");

                // Tous les dons d'un donneur
            String hqlDonneurs = "FROM Don d WHERE d.donneur.nom = :nomDonneur";
            Query query = session.createQuery(hqlDonneurs);
            query.setParameter("nomDonneur", "Bouanani Abderrahman");
            List<Don> dons = query.list();
            
            System.out.println("\nDons de Bouanani Abderrahman :");
            for (Don d : dons) {
                System.out.println("- Le " + d.getDate() + " a " + d.getLieu());
            }

                // Donneurs par groupe sanguin
            String hqlGroupes = "FROM Donneur d WHERE d.groupeSanguin = :groupe";
            query = session.createQuery(hqlGroupes);
            query.setParameter("groupe", "O+");
            List<Donneur> donneursOPlus = query.list();
            
            System.out.println("\nDonneurs de groupe O+ :");
            for (Donneur d : donneursOPlus) {
                System.out.println("- " + d.getNom());
            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}