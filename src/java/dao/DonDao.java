package dao;

import entities.Don;
import entities.DonPK;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
        return entity;
    }
}