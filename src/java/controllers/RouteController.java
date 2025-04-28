package controllers;

import entities.Don;
import entities.Donneur;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher; // Modifié par v0
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.DonService;
import services.DonneurService;

@WebServlet(name = "RouteController", urlPatterns = {"/RouteController"}) // Modifié par v0
public class RouteController extends HttpServlet {

    private DonneurService donneurService;
    private DonService donService;

    @Override
    public void init() throws ServletException {
        super.init();
        donneurService = new DonneurService();
        donService = new DonService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String page = request.getParameter("page"); // Modifié par v0
        
        if (page == null) {
            // Redirection par défaut vers la page d'authentification
            RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
            dispatcher.forward(request, response); // Modifié par v0
            return;
        }
        
        switch (page) { // Modifié par v0
            // Pages d'authentification
            case "login":
                RequestDispatcher loginDispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
                loginDispatcher.forward(request, response); // Modifié par v0
                break;
                
            case "inscription":
                RequestDispatcher inscriptionDispatcher = request.getRequestDispatcher("/auth/Inscription.jsp"); // Modifié par v0
                inscriptionDispatcher.forward(request, response); // Modifié par v0
                break;
                
            case "forgotPassword":
                RequestDispatcher forgotDispatcher = request.getRequestDispatcher("/auth/forgotPassword.jsp"); // Modifié par v0
                forgotDispatcher.forward(request, response); // Modifié par v0
                break;
                
            case "verification":
                RequestDispatcher verificationDispatcher = request.getRequestDispatcher("/auth/verification.jsp"); // Modifié par v0
                verificationDispatcher.forward(request, response); // Modifié par v0
                break;
                
            // Pages principales
            case "users":
                RequestDispatcher usersDispatcher = request.getRequestDispatcher("/users.jsp"); // Modifié par v0
                usersDispatcher.forward(request, response); // Modifié par v0
                break;
                
            case "profil":
                RequestDispatcher profilDispatcher = request.getRequestDispatcher("/profil.jsp"); // Modifié par v0
                profilDispatcher.forward(request, response); // Modifié par v0
                break; 
                
            case "dons":
                RequestDispatcher donsDispatcher = request.getRequestDispatcher("/dons/page.jsp"); // Modifié par v0
                donsDispatcher.forward(request, response); // Modifié par v0
                break;
                
            case "donHistory":
                RequestDispatcher historyDispatcher = request.getRequestDispatcher("/donHistory.jsp"); // Modifié par v0
                historyDispatcher.forward(request, response); // Modifié par v0
                break;
                
            default:
                RequestDispatcher defaultDispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); // Modifié par v0
                defaultDispatcher.forward(request, response); // Modifié par v0
                break;
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
        return "Route Controller";
    }
}
