
package controllers;

import dao.DonDao;
import entities.Donneur;
import entities.Don;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher; // Modifié par v0
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.DonService;

@WebServlet(name = "ProfilDonneurController", urlPatterns = {"/ProfilDonneurController"})
public class ProfilDonneurController extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
   
        // Récupérer le donneur connecté depuis la session
        HttpSession session = request.getSession();
        Donneur donneur = (Donneur) session.getAttribute("donneur");
        
        System.out.println("**********controller works just fine***********");
        if (donneur != null) {
            // Récupérer les dons du donneur
            DonService donService = new DonService();
            List<Don> dons = donService.getDonsByDonneur(donneur.getIdUser());
            
            // Debug
            System.out.println("**********Donneur ID: " + donneur.getIdUser());
            System.out.println("Nombre de dons trouvés: " + (dons != null ? dons.size() : "null"));
            
            // Mettre les dons dans la request
            request.setAttribute("dons", dons);
        }
        
        // Forward vers la page profil.jsp
        request.getRequestDispatcher("/profil.jsp").forward(request, response);
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       processRequest(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       processRequest(request, response);
   }
}
