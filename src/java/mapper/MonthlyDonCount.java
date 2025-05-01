package mapper;

/**
 * Classe pour mapper les résultats de la requête de comptage des dons par mois
 */
public class MonthlyDonCount {
    private String monthName;
    private long donCount;

    public MonthlyDonCount(String monthName, long donCount) {
        this.monthName = monthName;
        this.donCount = donCount;
    }

    public String getMonthName() {
        return monthName;
    }

    public long getDonCount() {
        return donCount;
    }
}
