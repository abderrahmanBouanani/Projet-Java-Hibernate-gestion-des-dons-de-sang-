package controllers;

import entities.Donneur;
import java.io.IOException;
import javax.servlet.RequestDispatcher; // Modifié par v0
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
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
               dispatcher.forward(request, response); // Modifié par v0
           } else {
               String nom = request.getParameter("nom");
               String email = request.getParameter("email");
               String password = request.getParameter("mdp");
               String groupeSanguin = request.getParameter("groupeSanguin");                
               Donneur u = new Donneur(nom, email, password, groupeSanguin);
               u.setIdUser(Integer.parseInt(id));
               ds.update(u);
               
               RequestDispatcher dispatcher = request.getRequestDispatcher("/users.jsp"); // Modifié par v0
               dispatcher.forward(request, response); // Modifié par v0
           }
       } else if (op.equals("delete")) {
           String id = request.getParameter("id");
           ds.delete(ds.findById(Integer.parseInt(id)));
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("/users.jsp"); // Modifié par v0
           dispatcher.forward(request, response); // Modifié par v0
       } else if (op.equals("update")) {
           String id = request.getParameter("id");
           Donneur u = ds.findById(Integer.parseInt(id));
           
           request.setAttribute("id", u.getIdUser()); // Modifié par v0
           request.setAttribute("nom", u.getName()); // Modifié par v0
           request.setAttribute("email", u.getEmail()); // Modifié par v0
           request.setAttribute("mdp", u.getMotDePasse()); // Modifié par v0
           
           RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
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
