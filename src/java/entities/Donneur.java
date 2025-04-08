package entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Donneur extends User {
    private String groupeSanguin;
    
    @Temporal(TemporalType.DATE)
    private Date dateDernierDon;
    
    @OneToMany(mappedBy = "donneur")
    private List<Don> dons;

    public Donneur() {
    }

    public Donneur(String groupeSanguin, Date dateDernierDon, String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
        this.groupeSanguin = groupeSanguin;
        this.dateDernierDon = dateDernierDon;
    }

    // Getters and Setters
    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public Date getDateDernierDon() {
        return dateDernierDon;
    }

    public void setDateDernierDon(Date dateDernierDon) {
        this.dateDernierDon = dateDernierDon;
    }

    public List<Don> getDons() {
        return dons;
    }

    public void setDons(List<Don> dons) {
        this.dons = dons;
    }
}