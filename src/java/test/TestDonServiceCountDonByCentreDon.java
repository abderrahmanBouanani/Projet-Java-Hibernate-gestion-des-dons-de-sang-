package test;

import services.DonService;
import java.util.List;

public class TestDonServiceCountDonByCentreDon {
    
    public static void main(String[] args) {
        // Créer une instance de DonService
        DonService donService = new DonService();
        
        System.out.println("=== Test DonService.countDonByCentreDon ===");
        
        try {
            // Appeler la méthode du service à tester
            List<Object[]> stats = donService.countDonByCentreDon();
            
            // Afficher les résultats
            if (stats != null) {
                System.out.println("Nombre de centres avec des dons: " + stats.size());
                
                // Afficher les statistiques pour chaque centre
                for (Object[] stat : stats) {
                    System.out.println("-------------------");
                    System.out.println("Centre: " + stat[0]);
                    System.out.println("Nombre de dons: " + stat[1]);
                }
            } else {
                System.out.println("Aucune statistique trouvée (null)");
            }
            
        } catch (Exception e) {
            System.out.println("Erreur lors du test du service:");
            e.printStackTrace();
        }
    }
}