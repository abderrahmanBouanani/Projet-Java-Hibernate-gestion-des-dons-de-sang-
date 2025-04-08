package test;

import util.HibernateUtil;

public class TestCreation {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        System.out.println("SessionFactory creee avec succès!");
    }
}