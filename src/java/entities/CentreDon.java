package entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CentreDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nom;
    private String adresse;
    
    @OneToMany(mappedBy = "centreDon")
    private List<Don> dons;

    public CentreDon() {
    }

    public CentreDon(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Don> getDons() {
        return dons;
    }

    public void setDons(List<Don> dons) {
        this.dons = dons;
    }
}