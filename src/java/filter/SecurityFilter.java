package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        boolean isLoggedIn = (session != null && (session.getAttribute("donneur") != null || session.getAttribute("admin") != null));
        String requestURI = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        
        // Permettre l'accès aux ressources statiques et aux pages d'authentification
        if (requestURI.contains("/css/") || 
            requestURI.contains("/js/") || 
            requestURI.contains("/images/") ||
            requestURI.contains("/auth/") ||
            requestURI.contains("/welcome.jsp") ||
            // Permettre explicitement les contrôleurs d'authentification
            requestURI.contains("/AuthentificationController") ||
            requestURI.contains("/InscriptionController") ||
            requestURI.contains("/Mdob") ||
            requestURI.contains("/Verfier") ||
            requestURI.contains("/UpdatePasswordController") ||
            // Permettre le RouteController pour les pages d'authentification
            (requestURI.contains("/RouteController") && httpRequest.getParameter("page") != null && 
             (httpRequest.getParameter("page").equals("login") || 
              httpRequest.getParameter("page").equals("inscription") || 
              httpRequest.getParameter("page").equals("forgotPassword") ||
              httpRequest.getParameter("page").equals("verification") ||
              httpRequest.getParameter("page").equals("updatePassword")))) {
            
            chain.doFilter(request, response);
            return;
        }
        
        // Vérifier si c'est une action de modification (POST, PUT, DELETE) ou une action spécifique
        // Exclure les contrôleurs d'authentification de cette vérification
        boolean isActionRequest = ("POST".equalsIgnoreCase(method) || 
                                  "PUT".equalsIgnoreCase(method) || 
                                  "DELETE".equalsIgnoreCase(method)) &&
                                  !requestURI.contains("/AuthentificationController") &&
                                  !requestURI.contains("/InscriptionController") &&
                                  !requestURI.contains("/Mdob") &&
                                  !requestURI.contains("/Verfier") &&
                                  !requestURI.contains("/UpdatePasswordController") ||
                                  (requestURI.contains("Controller") && 
                                   !requestURI.contains("RouteController") &&
                                   !requestURI.contains("/AuthentificationController") &&
                                   !requestURI.contains("/InscriptionController") &&
                                   !requestURI.contains("/Mdob") &&
                                   !requestURI.contains("/Verfier") &&
                                   !requestURI.contains("/UpdatePasswordController"));
        
        // Si c'est une action et que l'utilisateur n'est pas connecté, rediriger vers la page de connexion
        if (isActionRequest && !isLoggedIn) {
            httpRequest.setAttribute("error", "Veuillez vous connecter pour effectuer cette action");
            httpRequest.getRequestDispatcher("/RouteController?page=login").forward(httpRequest, httpResponse);
            return;
        }
        
        // Pour les pages protégées qui nécessitent des droits d'administrateur
        boolean isAdminPage = requestURI.contains("/users.jsp") || 
                             requestURI.contains("/dons/") ||
                             (requestURI.contains("/RouteController") && 
                              httpRequest.getParameter("page") != null && 
                              (httpRequest.getParameter("page").equals("users") || 
                               httpRequest.getParameter("page").equals("dons")));
        
        boolean isAdmin = session != null && session.getAttribute("admin") != null;
        
        // Si c'est une page admin et que l'utilisateur n'est pas admin, afficher un message
        if (isAdminPage && !isAdmin && isActionRequest) {
            httpRequest.setAttribute("error", "Accès non autorisé. Cette page est réservée aux administrateurs.");
            httpRequest.getRequestDispatcher("/RouteController?page=login").forward(httpRequest, httpResponse);
            return;
        }
        
        // Continuer la chaîne des filtres
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
