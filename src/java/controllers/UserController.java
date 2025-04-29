package controllers;

import entities.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher; 
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
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
               dispatcher.forward(request, response); 
           } else {
               String nom = request.getParameter("nom");
               String email = request.getParameter("email");
               String pwd = request.getParameter("pwd");
               User u = new User(nom, email, pwd);
               u.setIdUser(Integer.parseInt(id));
               us.update(u);
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=users"); 
               dispatcher.forward(request, response); 
           }
       } else if (op.equals("delete")) {
           String id = request.getParameter("id");
           us.delete(us.findById(Integer.parseInt(id)));
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=users"); 
           dispatcher.forward(request, response); 
       } else if (op.equals("update")) {
           String id = request.getParameter("id");
           User u = us.findById(Integer.parseInt(id));
           
           request.setAttribute("id", u.getIdUser()); 
           request.setAttribute("nom", u.getName()); 
           request.setAttribute("email", u.getEmail()); 
           request.setAttribute("pwd", u.getMotDePasse()); 
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=inscription"); 
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
       return "Short description";
   }

}
