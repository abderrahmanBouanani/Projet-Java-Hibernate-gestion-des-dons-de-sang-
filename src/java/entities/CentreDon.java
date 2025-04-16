package entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "centres_don")
public class CentreDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_centre")
    private Integer idCentre;
    
    private String adresseCentre;
    
    @OneToMany(mappedBy = "centreDon")
    private List<Don> dons;

    // Constructeurs
    public CentreDon() {}

    public CentreDon(String adresseCentre) {
        this.adresseCentre = adresseCentre;
    }
    
    //getters, setters...

    public Integer getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(Integer idCentre) {
        this.idCentre = idCentre;
    }

    public String getAdresseCentre() {
        return adresseCentre;
    }

    public void setAdresseCentre(String adresseCentre) {
        this.adresseCentre = adresseCentre;
    }

    public List<Don> getDons() {
        return dons;
    }

    public void setDons(List<Don> dons) {
        this.dons = dons;
    }
    
}