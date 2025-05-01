package mapper;

/**
 * Classe pour mapper les résultats de la requête de comptage des dons par centre
 */
public class CentreDonCount {
    private String centreName;
    private long donCount;

    public CentreDonCount(String centreName, long donCount) {
        this.centreName = centreName;
        this.donCount = donCount;
    }

    public String getCentreName() {
        return centreName;
    }

    public long getDonCount() {
        return donCount;
    }
}
