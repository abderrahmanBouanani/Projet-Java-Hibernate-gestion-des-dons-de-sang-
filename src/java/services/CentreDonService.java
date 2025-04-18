package services;

import dao.CentreDonDao;
import entities.CentreDon;
import java.util.List;

public class CentreDonService implements IService<CentreDon> {
    private final CentreDonDao dao;

    public CentreDonService() {
        this.dao = new CentreDonDao();
    }

    @Override
    public boolean create(CentreDon o) {
        return dao.create(o);
    }

    @Override
    public boolean delete(CentreDon o) {
        return dao.delete(o);
    }

    @Override
    public boolean update(CentreDon o) {
        return dao.update(o);
    }

    @Override
    public List<CentreDon> findAll() {
        return dao.findAll();
    }

    @Override
    public CentreDon findById(int id) {
        return dao.findById(id);
    }
}