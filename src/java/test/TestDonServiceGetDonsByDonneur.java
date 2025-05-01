package test;

import services.DonService;
import entities.Don;
import java.util.List;

public class TestDonServiceGetDonsByDonneur {
    
    public static void main(String[] args) {
        // Créer une instance de DonService
        DonService donService = new DonService();
        
        // ID du donneur à tester
        int idDonneurTest = 2;
        
        System.out.println("=== Test DonService.getDonsByDonneur ===");
        System.out.println("Recherche des dons pour le donneur ID: " + idDonneurTest);
        
        try {
            // Appeler la méthode du service à tester
            List<Don> dons = donService.getDonsByDonneur(idDonneurTest);
            
            // Afficher les résultats
            if (dons != null) {
                System.out.println("Nombre de dons trouvés: " + dons.size());
                
                // Afficher les détails de chaque don
                for (Don don : dons) {
                    System.out.println("-------------------");
                    System.out.println("ID Don: " + don.getId());
                    System.out.println("Date: " + don.getId().getDateDon());
                    System.out.println("Centre: " + don.getCentreDon().getNameCentre());
                    System.out.println("Donneur: " + don.getDonneur().getName());
                }
            } else {
                System.out.println("Aucun don trouvé (null)");
            }
            
        } catch (Exception e) {
            System.out.println("Erreur lors du test du service:");
            e.printStackTrace();
        }
    }
}
