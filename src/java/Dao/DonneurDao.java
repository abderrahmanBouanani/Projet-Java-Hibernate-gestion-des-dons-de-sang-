package dao;

import entities.CentreDon;
import entities.Don;
import entities.Donneur;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class DonneurDao extends AbstractDao<Donneur> {
    public DonneurDao() {
        super(Donneur.class);
    }
    
    // Méthode HQL 1: Recherche par groupe sanguin
    public List<Donneur> findByBloodGroup(String groupeSanguin) {
        Session session = null;
        Transaction tx = null;
        List<Donneur> donneurs = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            donneurs = session.getNamedQuery("findByBloodGroup")
                             .setParameter("groupe", groupeSanguin)
                             .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
        return donneurs;
    }

    // Méthode HQL 2: Donneurs avec dons après une date donnée
    public List<Donneur> findDonorsWithDonationsAfterDate(Date date) {
        Session session = null;
        Transaction tx = null;
        List<Donneur> donneurs = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            donneurs = session.getNamedQuery("findDonorsWithDonationsAfterDate")
                             .setParameter("date", date)
                             .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
        return donneurs;
    }

    // Méthode Native 1: Centres de don fréquentés par un donneur
    public List<CentreDon> findDonationCentersByDonor(Donneur donneur) {
        Session session = null;
        Transaction tx = null;
        List<CentreDon> centres = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            centres = session.getNamedQuery("findDonationCentersByDonor")
                           .setParameter("donneurId", donneur.getIdUser())
                           .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
        return centres;
    }
}