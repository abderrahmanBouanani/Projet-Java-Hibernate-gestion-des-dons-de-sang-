package controllers;

import entities.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher; // Modifié par v0
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DonneurService;
import services.UserService;

/**
*
* @author Admin
*/
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

   private UserService us;

   @Override
   public void init() throws ServletException {
       super.init();
       us = new UserService();
   }

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       String op = request.getParameter("op");
       if (op == null) {
           String id = request.getParameter("id");
           if (id == null || id.isEmpty()) {
               String nom = request.getParameter("nom");
               String email = request.getParameter("email");
               String pwd = request.getParameter("pwd");
               us.create(new User(nom, email, pwd));
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
               dispatcher.forward(request, response); // Modifié par v0
           } else {
               String nom = request.getParameter("nom");
               String email = request.getParameter("email");
               String pwd = request.getParameter("pwd");
               User u = new User(nom, email, pwd);
               u.setIdUser(Integer.parseInt(id));
               us.update(u);
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("/users.jsp"); // Modifié par v0
               dispatcher.forward(request, response); // Modifié par v0
           }
       } else if (op.equals("delete")) {
           String id = request.getParameter("id");
           us.delete(us.findById(Integer.parseInt(id)));
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("/users.jsp"); // Modifié par v0
           dispatcher.forward(request, response); // Modifié par v0
       } else if (op.equals("update")) {
           String id = request.getParameter("id");
           User u = us.findById(Integer.parseInt(id));
           
           request.setAttribute("id", u.getIdUser()); // Modifié par v0
           request.setAttribute("nom", u.getName()); // Modifié par v0
           request.setAttribute("email", u.getEmail()); // Modifié par v0
           request.setAttribute("pwd", u.getMotDePasse()); // Modifié par v0
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Inscription.jsp"); // Modifié par v0
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
