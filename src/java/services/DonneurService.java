package services;

import dao.DonneurDao;
import entities.CentreDon;
import entities.Donneur;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class DonneurService implements IService<Donneur> {
    private final DonneurDao dao;

    public DonneurService() {
        this.dao = new DonneurDao();
    }

    @Override
    public boolean create(Donneur o) {
        return dao.create(o);
    }

    @Override
    public boolean delete(Donneur o) {
        return dao.delete(o);
    }

    @Override
    public boolean update(Donneur o) {
        return dao.update(o);
    }

    @Override
    public List<Donneur> findAll() {
        return dao.findAll();
    }

    @Override
    public Donneur findById(int id) {
        return dao.findById(id);
    }
    
    // Méthodes spécifiques
    public List<Donneur> findByBloodGroup(String groupeSanguin) {
        return dao.findByBloodGroup(groupeSanguin);
    }

    public List<Donneur> findDonorsWithDonationsAfterDate(Date date) {
        return dao.findDonorsWithDonationsAfterDate(date);
    }

    public List<CentreDon> findDonationCentersByDonor(Donneur donneur) {
        return dao.findDonationCentersByDonor(donneur);
    }

    public Donneur findDonneurByEmail(String email) {
        Session session = null;
        Transaction tx = null;
        Donneur donneur = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            donneur = (Donneur) session.getNamedQuery("findDonneurByEmail").setParameter("email", email).uniqueResult();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return donneur;
    }
}
