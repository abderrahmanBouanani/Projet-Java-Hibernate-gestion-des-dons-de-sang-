package dao;

import entities.CentreDon;
import entities.Donneur;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class CentreDonDao extends AbstractDao<CentreDon> {
    public CentreDonDao() {
        super(CentreDon.class);
    } 
}