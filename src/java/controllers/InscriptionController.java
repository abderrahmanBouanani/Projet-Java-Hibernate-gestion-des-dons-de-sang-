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
           String id = request.getParameter("id");
           if (id == null || id.isEmpty()) {
               String nom = request.getParameter("nom");
               String email = request.getParameter("email");
               String password = request.getParameter("mdp");
               String groupeSanguin = request.getParameter("groupeSanguin");
               ds.create(new Donneur(nom, email, password, groupeSanguin));
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
               dispatcher.forward(request, response); 
           } else {
               String nom = request.getParameter("nom");
               String email = request.getParameter("email");
               String password = request.getParameter("mdp");
               String groupeSanguin = request.getParameter("groupeSanguin");                
               Donneur u = new Donneur(nom, email, password, groupeSanguin);
               u.setIdUser(Integer.parseInt(id));
               ds.update(u);
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=users"); 
               dispatcher.forward(request, response); 
           }
       } else if (op.equals("delete")) {
           String id = request.getParameter("id");
           ds.delete(ds.findById(Integer.parseInt(id)));
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=users"); 
           dispatcher.forward(request, response); 
       } else if (op.equals("update")) {
           String id = request.getParameter("id");
           Donneur u = ds.findById(Integer.parseInt(id));
           
           request.setAttribute("id", u.getIdUser()); 
           request.setAttribute("nom", u.getName()); 
           request.setAttribute("email", u.getEmail()); 
           request.setAttribute("mdp", u.getMotDePasse()); 
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
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
