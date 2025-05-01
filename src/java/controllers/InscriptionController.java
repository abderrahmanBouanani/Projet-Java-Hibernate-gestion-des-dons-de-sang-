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
           
           // Validation des champs
           if (nom == null || nom.trim().isEmpty() || 
               email == null || email.trim().isEmpty() || 
               password == null || password.trim().isEmpty() || 
               groupeSanguin == null || groupeSanguin.trim().isEmpty()) {
               
               request.setAttribute("error", "Veuillez remplir tous les champs");
               // Conserver les valeurs saisies
               request.setAttribute("nom", nom);
               request.setAttribute("email", email);
               request.setAttribute("groupeSanguin", groupeSanguin);
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=inscription"); 
               dispatcher.forward(request, response);
               return;
           }
           
           // Vérifier si l'email existe déjà
           Donneur existingDonneur = ds.findDonneurByEmail(email);
           if (existingDonneur != null) {
               request.setAttribute("error", "Cet email est déjà utilisé");
               // Conserver les valeurs saisies
               request.setAttribute("nom", nom);
               request.setAttribute("groupeSanguin", groupeSanguin);
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=inscription"); 
               dispatcher.forward(request, response);
               return;
           }
           
           // Vérifier la longueur du mot de passe
           if (password.length() < 6) {
               request.setAttribute("error", "Le mot de passe doit contenir au moins 6 caractères");
               // Conserver les valeurs saisies
               request.setAttribute("nom", nom);
               request.setAttribute("email", email);
               request.setAttribute("groupeSanguin", groupeSanguin);
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=inscription"); 
               dispatcher.forward(request, response);
               return;
           }
           
           ds.create(new Donneur(nom, email, password, groupeSanguin));
           
           // Rediriger vers la page de connexion avec un message de succès
           request.setAttribute("successMessage", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
           dispatcher.forward(request, response);
       } else if (op.equals("delete")) {
           String id = request.getParameter("id");
           ds.delete(ds.findById(Integer.parseInt(id)));
           
           request.setAttribute("successMessage", "Donneur supprimé avec succès");
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
