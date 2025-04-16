package dao;

import entities.Don;
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
}