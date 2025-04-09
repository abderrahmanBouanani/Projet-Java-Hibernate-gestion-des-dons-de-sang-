package entities;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
    private String matricule;

    public Admin() {
        super();
    }

    public Admin(String matricule, String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        this.matricule = matricule;
    }

    // Getters & Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}