package entities;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "donneurs")
@NamedQueries({
    @NamedQuery(
        name = "findByBloodGroup", 
        query = "FROM Donneur d WHERE d.groupeSanguin = :groupe"
    ),
    @NamedQuery(
        name = "findDonorsWithDonationsAfterDate", 
        query = "SELECT DISTINCT d FROM Donneur d JOIN d.dons don WHERE don.id.dateDon > :date"
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "findDonationCentersByDonor", 
        query = "SELECT cd.* FROM centres_don cd " +
                "JOIN dons d ON cd.id_centre = d.id_centre " +
                "WHERE d.id_donneur = :donneurId",
        resultClass = CentreDon.class
    )
})
public class Donneur extends User {

    private String groupeSanguin;

    @OneToMany(mappedBy = "donneur")
    private List<Don> dons;

    public Donneur() {}

    public Donneur(String name, String email, String motDePasse, String groupeSanguin) {
        super(name, email, motDePasse);
        this.groupeSanguin = groupeSanguin;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public List<Don> getDons() {
        return dons;
    }

    public void setDons(List<Don> dons) {
        this.dons = dons;
    }
}
