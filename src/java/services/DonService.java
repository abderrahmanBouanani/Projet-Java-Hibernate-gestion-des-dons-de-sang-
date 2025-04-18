package services;

import dao.DonDao;
import entities.Don;
import entities.DonPK;
import java.util.List;

public class DonService implements IService<Don> {
    private final DonDao dao;

    public DonService() {
        this.dao = new DonDao();
    }

    @Override
    public boolean create(Don o) {
        return dao.create(o);
    }

    @Override
    public boolean delete(Don o) {
        return dao.delete(o);
    }

    @Override
    public boolean update(Don o) {
        return dao.update(o);
    }

    @Override
    public List<Don> findAll() {
        return dao.findAll();
    }

    @Override
    public Don findById(int id) {
        return dao.findById(id);
    }

    public Don findByIdPk(DonPK id) {
        return dao.findByIdPk(id);
    }
}