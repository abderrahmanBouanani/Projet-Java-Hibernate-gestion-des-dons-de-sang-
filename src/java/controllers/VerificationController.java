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

/**
 *
 * @author Admin
 */
@WebServlet(name = "VerificationController", urlPatterns = {"/Verfier"})
public class VerificationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String codeVerification = (String) session.getAttribute("code_verification");
        String codeEntre = request.getParameter("code");
        
        if (codeVerification != null && codeEntre != null && codeVerification.equals(codeEntre)) {
            // Code correct, rediriger vers la page de mise à jour du mot de passe
            RequestDispatcher dispatcher = request.getRequestDispatcher("/RouteController?page=updatePassword");
            dispatcher.forward(request, response);
        } else {
            // Code incorrect, rediriger vers la page de vérification avec un message d'erreur
            request.setAttribute("msg", "Code de vérification incorrect");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/verification.jsp");
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
        return "Verification Controller";
    }
}
