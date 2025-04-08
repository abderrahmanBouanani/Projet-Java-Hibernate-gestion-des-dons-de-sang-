package test;

import dao.UserDao;
import entities.User;
import java.util.List;

public class TestUser {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        // Creation d'un utilisateur
        User user = new User("Bouanani Abderrahman", "bouanani@mail.com", "password123");
        userDao.create(user);
        System.out.println("✅ Utilisateur cree : " + user.getNom());

        // Recuperation par ID
        User userFromDB = userDao.findById(user.getId());
        System.out.println("🔍 Utilisateur recupere : " + userFromDB.getEmail());

        // Mise à jour
        userFromDB.setMotDePasse("newPassword456");
        userDao.update(userFromDB);
        System.out.println("🔄 Mot de passe mis a jour");

        // Lister tous les utilisateurs
        List<User> users = userDao.findAll();
        System.out.println("📋 Liste des utilisateurs :");
        for (User u : users) {
            System.out.println("- " + u.getNom() + " (" + u.getEmail() + ")");
        }

        // Suppression (optionnel)
        // userDao.delete(userFromDB);
        //System.out.println("🗑 Utilisateur supprime");
    }
}