package controllers;

import entities.Admin;
import entities.Donneur;
import entities.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher; // Modifié par v0
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AdminService;
import services.DonneurService;
import services.UserService;
import util.Util;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AuthentificationController", urlPatterns = {"/AuthentificationController"})
public class AuthentificationController extends HttpServlet {

   private UserService us;
   private AdminService as;
   private DonneurService ds;

   @Override
   public void init() throws ServletException {
       super.init();
       us = new UserService();
   }

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       String email = request.getParameter("email");
       String password = request.getParameter("password");

       as = new AdminService();
       ds = new DonneurService();

       Admin admin = as.findAdminByEmail(email);
       if (admin != null) {
           if (admin.getMotDePasse().equals(password)) {
               HttpSession session = request.getSession();
               session.setAttribute("admin", admin);
               RequestDispatcher dispatcher = request.getRequestDispatcher("/users.jsp"); // Modifié par v0
               dispatcher.forward(request, response); // Modifié par v0
               return;
           } else {
               request.setAttribute("msg", "Mot de passe incorrect"); // Modifié par v0
               RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
               dispatcher.forward(request, response); // Modifié par v0
               return;
           }
       }

       Donneur donneur = ds.findDonneurByEmail(email);
       if (donneur != null) {
           if (donneur.getMotDePasse().equals(password)) {
               HttpSession session = request.getSession();
               session.setAttribute("donneur", donneur);
               RequestDispatcher dispatcher = request.getRequestDispatcher("/profil.jsp"); // Modifié par v0
               dispatcher.forward(request, response); // Modifié par v0
               return;
           } else {
               request.setAttribute("msg", "Mot de passe incorrect"); // Modifié par v0
               RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
               dispatcher.forward(request, response); // Modifié par v0
               return;
           }
       }

       request.setAttribute("msg", "Email introuvable"); // Modifié par v0
       RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
       dispatcher.forward(request, response); // Modifié par v0
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
