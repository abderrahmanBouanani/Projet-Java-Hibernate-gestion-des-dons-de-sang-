package test;

import dao.AdminDao;
import entities.Admin;

public class TestAdmin {
    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();

        // Creation
        Admin admin = new Admin("ADM-1234", "Bn Admin", "admin@centre.com", "Pass123");
        adminDao.create(admin);
        System.out.println("✅ Admin créé : " + admin.getMatricule());

        // Recuperation
        Admin adminFromDB = adminDao.findById(admin.getId());
        System.out.println("🔍 Admin trouvé : " + adminFromDB.getNom() + " (" + adminFromDB.getMatricule() + ")");
    }
}