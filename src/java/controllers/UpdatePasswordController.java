package controllers;

import entities.Donneur;
import java.io.IOException;
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.DonneurService;

/**
*
* @author Admin
*/
@WebServlet(name = "UpdatePasswordController", urlPatterns = {"/UpdatePasswordController"})
public class UpdatePasswordController extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       String password = request.getParameter("password");
       String passwordcnf = request.getParameter("passwordcnf");
       DonneurService ds = new DonneurService();
       
       if (password == null || passwordcnf == null || password.isEmpty() || passwordcnf.isEmpty()) {
           request.setAttribute("msg", "Veuillez remplir tous les champs");
           RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/updateMotdePasse.jsp");
           dispatcher.forward(request, response);
           return;
       }
       
       if (password.equals(passwordcnf)) {
           HttpSession session = request.getSession();
           Donneur d = (Donneur) session.getAttribute("donneur");
           if (d == null) {
               // Si l'utilisateur n'est pas connecté, vérifier s'il y a un email en session
               String email = (String) session.getAttribute("reset_email");
               if (email != null) {
                   d = ds.findDonneurByEmail(email);
                   if (d == null) {
                       request.setAttribute("msg", "Utilisateur non trouvé");
                       RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/forgotPassword.jsp");
                       dispatcher.forward(request, response);
                       return;
                   }
               } else {
                   response.sendRedirect(request.getContextPath() + "/RouteController?page=login");
                   return;
               }
           }
           
           // Mettre à jour le mot de passe sans hachage
           d.setMotDePasse(password);
           ds.update(d);
           
           // Mettre à jour la session si l'utilisateur est connecté
           if (session.getAttribute("donneur") != null) {
               session.setAttribute("donneur", d);
           }
           
           // Nettoyer les attributs de session utilisés pour la réinitialisation
           session.removeAttribute("reset_email");
           session.removeAttribute("code_verification");
           
           // Rediriger vers la page de connexion avec un message de succès
           request.setAttribute("success", "Mot de passe mis à jour avec succès");
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login");
           dispatcher.forward(request, response);
       } else {
           request.setAttribute("msg", "Les mots de passe ne correspondent pas");
           RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/updateMotdePasse.jsp");
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
       return "Update Password Controller";
   }
}
