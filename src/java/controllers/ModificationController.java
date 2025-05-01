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

/**
 *
 * @author Admin
 */
@WebServlet(name = "ModificationController", urlPatterns = {"/ModificationController"})
public class ModificationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DonneurService ds = new DonneurService();

        String op = request.getParameter("op");
        
        // Si op est "update", charger les données pour modification
        if (op != null && op.equals("update")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                Donneur donneur = ds.findById(Integer.parseInt(id));
                if (donneur != null) {
                    request.setAttribute("donneur", donneur);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=modification");
                    dispatcher.forward(request, response);
                    return;
                }
            }
        } 
        // Sinon, traiter la soumission du formulaire de modification
        else {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                String nom = request.getParameter("nom");
                String email = request.getParameter("email");
                String password = request.getParameter("mdp");
                String groupeSanguin = request.getParameter("groupeSanguin");
                
                // Vérifier si l'email existe déjà pour un autre utilisateur
                Donneur existingDonneur = ds.findDonneurByEmail(email);
                if (existingDonneur != null && existingDonneur.getIdUser() != Integer.parseInt(id)) {
                    request.setAttribute("error", "Cet email est déjà utilisé par un autre compte");
                    request.setAttribute("donneur", ds.findById(Integer.parseInt(id)));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=modification"); 
                    dispatcher.forward(request, response);
                    return;
                }
                
                Donneur d = new Donneur(nom, email, password, groupeSanguin);
                d.setIdUser(Integer.parseInt(id));
                ds.update(d);
                
                // Mettre à jour la session si c'est le donneur connecté qui modifie son profil
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("donneur") != null) {
                    Donneur sessionDonneur = (Donneur) session.getAttribute("donneur");
                    if (sessionDonneur.getIdUser() == Integer.parseInt(id)) {
                        session.setAttribute("donneur", d);
                    }
                }
                
                // Rediriger vers la page appropriée
                if (session != null && session.getAttribute("donneur") != null) {
                    request.setAttribute("success", "Profil mis à jour avec succès");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=profil");
                    dispatcher.forward(request, response);
                } else if (session != null && session.getAttribute("admin") != null) {
                    request.setAttribute("success", "Donneur mis à jour avec succès");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=users");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/RouteController?page=login");
                }
            } else {
                // Rediriger vers la page de profil si aucun ID n'est fourni
                response.sendRedirect(request.getContextPath() + "/RouteController?page=profil");
            }
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
        return "Modification Controller";
    }
}
