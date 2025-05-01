package controllers;

import entities.Donneur;
import java.io.IOException;
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DonneurService;

/**
*
* @author Admin
*/
@WebServlet(name = "InscriptionController", urlPatterns = {"/InscriptionController"})
public class InscriptionController extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       DonneurService ds = new DonneurService();

       String op = request.getParameter("op");
       if (op == null) {
           // Création d'un nouveau donneur (inscription uniquement)
           String nom = request.getParameter("nom");
           String email = request.getParameter("email");
           String password = request.getParameter("mdp");
           String groupeSanguin = request.getParameter("groupeSanguin");
           
           // Vérifier si l'email existe déjà
           Donneur existingDonneur = ds.findDonneurByEmail(email);
           if (existingDonneur != null) {
               request.setAttribute("error", "Cet email est déjà utilisé");
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=inscription"); 
               dispatcher.forward(request, response);
               return;
           }
           
           ds.create(new Donneur(nom, email, password, groupeSanguin));
           
           // Rediriger vers la page de connexion avec un message de succès
           request.setAttribute("success", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
           dispatcher.forward(request, response);
       } else if (op.equals("delete")) {
           String id = request.getParameter("id");
           ds.delete(ds.findById(Integer.parseInt(id)));
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=users"); 
           dispatcher.forward(request, response); 
       }
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

   @Override
   public String getServletInfo() {
       return "Inscription Controller";
   }
}
