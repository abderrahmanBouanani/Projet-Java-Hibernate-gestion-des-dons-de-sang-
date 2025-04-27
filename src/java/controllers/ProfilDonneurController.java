package controllers;

import dao.DonDao;
import entities.Donneur;
import entities.Don;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.DonService;

@WebServlet(name = "ProfilDonneurController", urlPatterns = {"/ProfilDonneurController"})
public class ProfilDonneurController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Donneur donneur = (Donneur) session.getAttribute("donneur");

        if (donneur != null) {
            DonService donService = new DonService();
            List<Don> dons = donService.getDonsByDonneur(donneur.getIdUser());

            request.setAttribute("dons", dons);
            request.getRequestDispatcher("profil.jsp").forward(request, response);
        } else {
            response.sendRedirect("Authentification.jsp?msg=Session expirée");
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
}