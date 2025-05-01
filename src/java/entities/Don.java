package entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Don.findByDonneur", 
            query = "SELECT d FROM Don d LEFT JOIN FETCH d.centreDon WHERE d.donneur.idUser = :idDonneur"),
    @NamedQuery(
    name = "Don.countDonByCentreDon",
    query = "SELECT d.centreDon.name AS centreName, COUNT(d) AS donCount " +
           "FROM Don d LEFT JOIN d.centreDon " +
           "GROUP BY d.centreDon.name " +
           "ORDER BY donCount DESC")
})
@Table(name = "dons")
public class Don {
    @EmbeddedId
    private DonPK id = new DonPK();
    
    @ManyToOne
    @MapsId("donneurId")
    @JoinColumn(name = "id_donneur")
    private Donneur donneur;
    
    @ManyToOne
    @MapsId("centreDonId")
    @JoinColumn(name = "id_centre")
    private CentreDon centreDon;

    // Constructeurs
    public Don() {}

    public Don(Date dateDon, String lieuDon, Donneur donneur, CentreDon centreDon) {
        this.id = new DonPK(dateDon, lieuDon, donneur, centreDon);
        this.donneur = donneur;
        this.centreDon = centreDon;
    }

    // Getters/Setters...

    public DonPK getId() {
        return id;
    }

    public void setId(DonPK id) {
        this.id = id;
    }

    public Donneur getDonneur() {
        return donneur;
    }

    public void setDonneur(Donneur donneur) {
        this.donneur = donneur;
    }

    public CentreDon getCentreDon() {
        return centreDon;
    }

    public void setCentreDon(CentreDon centreDon) {
        this.centreDon = centreDon;
    }
    
}
