package entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    private String lieu;
    
    @ManyToOne
    private Donneur donneur;
    
    @ManyToOne
    private CentreDon centreDon;

    public Don() {
    }

    public Don(Date date, String lieu, Donneur donneur, CentreDon centreDon) {
        this.date = date;
        this.lieu = lieu;
        this.donneur = donneur;
        this.centreDon = centreDon; // peut être null
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
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