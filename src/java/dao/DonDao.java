package dao;

import entities.Don;
import entities.DonPK;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import util.HibernateUtil;

public class DonDao extends AbstractDao<Don> {

    public DonDao() {
        super(Don.class);
    }

    public Don findByIdPk(DonPK id) {
        Session session = null;
        Transaction tx = null;
        Don entity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            entity = (Don) session.get(Don.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entity;
    }

    public List<Don> getDonsByDonneur(int idDonneur) {
        Session session = null;
        Transaction tx = null;
        List<Don> dons = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            dons = session.getNamedQuery("Don.findByDonneur").setParameter("idDonneur", idDonneur).list();
            tx.commit();

            // Ajouter un log pour déboguer
            System.out.println("Dons trouvés pour le donneur " + idDonneur + ": " + (dons != null ? dons.size() : 0));
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return dons;
    }

    public List<Object[]> countDonByCentreDon() {
        Session session = null;
        Transaction tx = null;
        List<Object[]> stats = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            stats = session.getNamedQuery("Don.countDonByCentreDon").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return stats;
    }
    
    public List<Object[]> countDonsByMonth() {
        Session session = null;
        Transaction tx = null;
        List<Object[]> stats = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            String hql = "SELECT MONTH(d.id.dateDon) as mois, COUNT(d) as total " +
                         "FROM Don d " +
                         "GROUP BY MONTH(d.id.dateDon) " +
                         "ORDER BY mois";
            
            Query query = session.createQuery(hql);
            stats = query.list();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return stats;
    }
    
    public List<Object[]> countDonsByBloodGroup() {
        Session session = null;
        Transaction tx = null;
        List<Object[]> stats = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            String hql = "SELECT d.donneur.groupeSanguin as groupe, COUNT(d) as total " +
                         "FROM Don d " +
                         "GROUP BY d.donneur.groupeSanguin " +
                         "ORDER BY groupe";
            
            Query query = session.createQuery(hql);
            stats = query.list();
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return stats;
    }
}
