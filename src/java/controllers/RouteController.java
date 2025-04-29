package controllers;

import entities.Don;
import entities.Donneur;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.DonService;
import services.DonneurService;

@WebServlet(name = "RouteController", urlPatterns = {"/RouteController"}) 
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

        String page = request.getParameter("page");
        HttpSession session = request.getSession(false);
        
        // Si l'utilisateur essaie d'accéder à la page de login alors qu'il est déjà connecté
        if ("login".equals(page) && session != null && 
            (session.getAttribute("donneur") != null || session.getAttribute("admin") != null)) {
            
            // Déconnecter l'utilisateur
            session.invalidate();
        }        
        
        if (page == null) {
            // Redirection par défaut vers la page d'authentification
            RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); 
            dispatcher.forward(request, response); 
            return;
        }
        
        switch (page) { 
            // Pages d'authentification
            case "login":
                RequestDispatcher loginDispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); 
                loginDispatcher.forward(request, response); 
                break;
                
            case "inscription":
                RequestDispatcher inscriptionDispatcher = request.getRequestDispatcher("/auth/Inscription.jsp"); 
                inscriptionDispatcher.forward(request, response); 
                break;
                
            case "forgotPassword":
                RequestDispatcher forgotDispatcher = request.getRequestDispatcher("/auth/forgotPassword.jsp"); 
                forgotDispatcher.forward(request, response); 
                break;
                
            case "verification":
                RequestDispatcher verificationDispatcher = request.getRequestDispatcher("/auth/verification.jsp"); 
                verificationDispatcher.forward(request, response); 
                break;
                
            // Pages principales
            case "users":
                RequestDispatcher usersDispatcher = request.getRequestDispatcher("/users.jsp"); 
                usersDispatcher.forward(request, response); 
                break;
                
            case "profil":
                RequestDispatcher profilDispatcher = request.getRequestDispatcher("/profil.jsp"); 
                profilDispatcher.forward(request, response); 
                break; 
                
            case "dons":
                RequestDispatcher donsDispatcher = request.getRequestDispatcher("/dons/page.jsp"); 
                donsDispatcher.forward(request, response); 
                break;
                
            case "donHistory":
                RequestDispatcher historyDispatcher = request.getRequestDispatcher("/donHistory.jsp"); 
                historyDispatcher.forward(request, response); 
                break;
                
            case "donGraph":
                RequestDispatcher donGraphDispatcher = request.getRequestDispatcher("/donGraph.jsp"); 
                donGraphDispatcher.forward(request, response); 
                break;    
                
            default:
                RequestDispatcher defaultDispatcher = request.getRequestDispatcher("/auth/Authentification.jsp"); 
                defaultDispatcher.forward(request, response); 
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
