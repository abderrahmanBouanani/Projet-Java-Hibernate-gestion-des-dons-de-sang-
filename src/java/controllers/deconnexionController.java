package controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher; // Modifié par v0
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
*
* @author Admin
*/
@WebServlet(name = "deconnexionController", urlPatterns = {"/deconnexionController"})

public class deconnexionController extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

       HttpSession session = request.getSession();
       session.invalidate();
       
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
