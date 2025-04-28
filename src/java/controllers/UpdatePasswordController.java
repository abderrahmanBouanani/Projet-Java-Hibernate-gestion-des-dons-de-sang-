package controllers;

import entities.Donneur;
import java.io.IOException;
import javax.servlet.RequestDispatcher; // Modifié par v0
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.DonneurService;
import util.Util;

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
       if (password.equals(passwordcnf)) {
           HttpSession session = request.getSession();
           Donneur d = (Donneur) session.getAttribute("donneur");
           d.setMotDePasse(Util.md5(password));
           ds.update(d);
           
           request.setAttribute("email", d.getEmail()); // Modifié par v0
           RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
           dispatcher.forward(request, response); // Modifié par v0
       } else {
           request.setAttribute("msg", "Mot de passe incorrect"); // Modifié par v0
           RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/updateMotdePasse.jsp"); // Modifié par v0
           dispatcher.forward(request, response); // Modifié par v0
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
       return "Short description";
   }

}
