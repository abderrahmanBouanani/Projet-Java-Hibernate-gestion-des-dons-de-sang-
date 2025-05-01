package mapper;

/**
 * Classe pour mapper les résultats de la requête de comptage des dons par groupe sanguin
 */
public class BloodGroupCount {
    private String bloodGroup;
    private long donCount;

    public BloodGroupCount(String bloodGroup, long donCount) {
        this.bloodGroup = bloodGroup;
        this.donCount = donCount;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public long getDonCount() {
        return donCount;
    }
}
