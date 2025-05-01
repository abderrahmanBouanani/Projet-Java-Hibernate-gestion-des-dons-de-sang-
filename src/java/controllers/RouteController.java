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
            // Redirection par défaut vers la page d'accueil
            RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp"); 
            dispatcher.forward(request, response); 
            return;
        }
        
        // Transférer les messages d'erreur et de succès de la session vers les attributs de requête
        if (session != null) {
            if (session.getAttribute("successMessage") != null) {
                request.setAttribute("successMessage", session.getAttribute("successMessage"));
                session.removeAttribute("successMessage");
            }
            if (session.getAttribute("errorMessage") != null) {
                request.setAttribute("error", session.getAttribute("errorMessage"));
                session.removeAttribute("errorMessage");
            }
        }
        
        // Vérifier si l'utilisateur est connecté
        boolean isLoggedIn = (session != null && (session.getAttribute("donneur") != null || session.getAttribute("admin") != null));
        boolean isAdmin = (session != null && session.getAttribute("admin") != null);
        boolean isDonneur = (session != null && session.getAttribute("donneur") != null);
        
        // Ajouter un attribut pour indiquer le mode lecture seule
        if (!isLoggedIn) {
            request.setAttribute("readOnlyMode", true);
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
                
            case "updatePassword":
                RequestDispatcher updatePasswordDispatcher = request.getRequestDispatcher("/auth/updateMotdePasse.jsp"); 
                updatePasswordDispatcher.forward(request, response); 
                break;
                
            // Pages principales
            case "users":
                RequestDispatcher usersDispatcher = request.getRequestDispatcher("/users.jsp"); 
                usersDispatcher.forward(request, response); 
                break;
            
            case "modification":
                // Si l'utilisateur n'est pas connecté, rediriger vers la page de connexion
                if (!isLoggedIn) {
                    request.setAttribute("error", "Veuillez vous connecter pour modifier votre profil");
                    RequestDispatcher loginRedirect = request.getRequestDispatcher("/auth/Authentification.jsp");
                    loginRedirect.forward(request, response);
                    return;
                }
                
                // Récupérer l'ID du donneur à modifier
                String id = request.getParameter("id");
                if (id != null && !id.isEmpty()) {
                    Donneur donneur = donneurService.findById(Integer.parseInt(id));
                    if (donneur != null) {
                        request.setAttribute("donneur", donneur);
                    }
                }
                RequestDispatcher modificationDispatcher = request.getRequestDispatcher("/modification.jsp"); 
                modificationDispatcher.forward(request, response); 
                break;    
                
            case "profil":
                // Si l'utilisateur n'est pas connecté, afficher un message mais permettre la navigation
                if (!isLoggedIn) {
                    request.setAttribute("info", "Vous êtes en mode lecture seule. Connectez-vous pour accéder à toutes les fonctionnalités.");
                }
                
                RequestDispatcher profilDispatcher = request.getRequestDispatcher("/profil.jsp"); 
                profilDispatcher.forward(request, response); 
                break; 
                
            case "dons":
                // Si l'utilisateur n'est pas admin, afficher un message mais permettre la navigation
                if (!isAdmin && isActionRequest(request)) {
                    request.setAttribute("error", "Vous devez être administrateur pour effectuer cette action");
                    RequestDispatcher loginRedirect = request.getRequestDispatcher("/auth/Authentification.jsp");
                    loginRedirect.forward(request, response);
                    return;
                }
                
                RequestDispatcher donsDispatcher = request.getRequestDispatcher("/dons/page.jsp"); 
                donsDispatcher.forward(request, response); 
                break;
                
            case "donHistory":
                // Si l'utilisateur n'est pas connecté, afficher un message mais permettre la navigation
                if (!isLoggedIn) {
                    request.setAttribute("info", "Vous êtes en mode lecture seule. Connectez-vous pour accéder à toutes les fonctionnalités.");
                }
                
                RequestDispatcher historyDispatcher = request.getRequestDispatcher("/donHistory.jsp"); 
                historyDispatcher.forward(request, response); 
                break;
                
            case "donGraph":
                // Si l'utilisateur n'est pas connecté, afficher un message mais permettre la navigation
                if (!isLoggedIn) {
                    request.setAttribute("info", "Vous êtes en mode lecture seule. Connectez-vous pour accéder à toutes les fonctionnalités.");
                }
                
                RequestDispatcher donGraphDispatcher = request.getRequestDispatcher("/donGraph.jsp"); 
                donGraphDispatcher.forward(request, response); 
                break;    
                
            default:
                RequestDispatcher defaultDispatcher = request.getRequestDispatcher("/welcome.jsp"); 
                defaultDispatcher.forward(request, response); 
                break;
        }
    }
    
    // Méthode pour vérifier si la requête est une action (POST, PUT, DELETE)
    private boolean isActionRequest(HttpServletRequest request) {
        String method = request.getMethod();
        return "POST".equalsIgnoreCase(method) || 
               "PUT".equalsIgnoreCase(method) || 
               "DELETE".equalsIgnoreCase(method);
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
