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
import services.SendMail;

/**
*
* @author Admin
*/
@WebServlet(name = "Mdob", urlPatterns = {"/Mdob"})
public class Mdob extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       String email = request.getParameter("email");
       DonneurService ds = new DonneurService();
       Donneur d = ds.findDonneurByEmail(email);
       if (d != null) {
           String code = String.format("%06d", (int) (Math.random() * 1000000));

           HttpSession session = request.getSession();
           session.setAttribute("donneur", d);
           session.setAttribute("code_verification", code);

           SendMail sed = new SendMail();
           sed.send(code, email);

           RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/verification.jsp"); 
           dispatcher.forward(request, response); 
       } else {
           request.setAttribute("msg", "Email n'existe pas"); 
           RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/forgotPassword.jsp"); 
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
