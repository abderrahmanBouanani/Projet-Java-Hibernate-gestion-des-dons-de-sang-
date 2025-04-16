package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class DonPK implements Serializable {
    @Temporal(TemporalType.DATE)
    private Date dateDon;
    
    @Column(name = "lieu_don")
    private String lieuDon;
    
    @ManyToOne
    @JoinColumn(name = "id_donneur")
    private Donneur donneur;
    
    @ManyToOne
    @JoinColumn(name = "id_centre")
    private CentreDon centreDon;

    // Constructeurs
    public DonPK() {}

    public DonPK(Date dateDon, String lieuDon, Donneur donneur, CentreDon centreDon) {
        this.dateDon = dateDon;
        this.lieuDon = lieuDon;
        this.donneur = donneur;
        this.centreDon = centreDon;
    }
    
    //getter and setters

    public Date getDateDon() {
        return dateDon;
    }

    public void setDateDon(Date dateDon) {
        this.dateDon = dateDon;
    }

    public String getLieuDon() {
        return lieuDon;
    }

    public void setLieuDon(String lieuDon) {
        this.lieuDon = lieuDon;
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