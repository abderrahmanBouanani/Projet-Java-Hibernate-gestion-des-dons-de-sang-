package controllers;

import entities.Admin;
import entities.Donneur;
import java.io.IOException;
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AdminService;
import services.DonneurService;
import services.UserService;

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
       HttpSession session = request.getSession();
       
       session.removeAttribute("donneur");
       session.removeAttribute("admin");

       String email = request.getParameter("email");
       String password = request.getParameter("password");

       as = new AdminService();
       ds = new DonneurService();

       Admin admin = as.findAdminByEmail(email);
       if (admin != null) {
           if (admin.getMotDePasse().equals(password)) {
               session.setAttribute("admin", admin);
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=users"); 
               dispatcher.forward(request, response); 
               return;
           } else {
               request.setAttribute("msg", "Mot de passe incorrect"); 
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
               dispatcher.forward(request, response); 
               return;
           }
       }

       Donneur donneur = ds.findDonneurByEmail(email);
       if (donneur != null) {
           if (donneur.getMotDePasse().equals(password)) {
               session.setAttribute("donneur", donneur);
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=profil"); 
               dispatcher.forward(request, response); 
               return;
           } else {
               request.setAttribute("msg", "Mot de passe incorrect"); 
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
               dispatcher.forward(request, response); 
               return;
           }
       }

       request.setAttribute("msg", "Email introuvable"); 
       RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
       dispatcher.forward(request, response); 
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
