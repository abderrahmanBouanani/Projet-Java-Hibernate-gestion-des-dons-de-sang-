package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "findAdminByEmail", query = "SELECT a FROM Admin a WHERE a.email = :email")
@Table(name = "admins")
public class Admin extends User {
    private String matricule;

    // Constructeurs
    public Admin() {}

    public Admin(String name, String email, String motDePasse, String matricule) {
        super(name, email, motDePasse);
        this.matricule = matricule;
    }
    
    //getters, setters...

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
}
