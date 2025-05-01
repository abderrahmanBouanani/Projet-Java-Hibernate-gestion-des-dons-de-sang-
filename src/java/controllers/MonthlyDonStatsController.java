package controllers;

import com.google.gson.Gson;
import dao.DonDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mapper.MonthlyDonCount;

@WebServlet(name = "MonthlyDonStatsController", urlPatterns = {"/MonthlyDonStatsController"})
public class MonthlyDonStatsController extends HttpServlet {

    private final DonDao dao = new DonDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Récupérer les résultats de la méthode DAO
        List<Object[]> resultats = dao.countDonsByMonth();

        // 2. Transformer en liste de DTO
        List<MonthlyDonCount> stats = new ArrayList<>();
        for (Object[] ligne : resultats) {
            int month = ((Number) ligne[0]).intValue();
            Long count = (Long) ligne[1];
            
            // Convertir le numéro du mois en nom du mois
            String monthName = getMonthName(month);
            stats.add(new MonthlyDonCount(monthName, count));
        }

        // 3. Sérialiser en JSON
        String json = new Gson().toJson(stats);

        // 4. Envoyer la réponse
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private String getMonthName(int month) {
        String[] monthNames = {"Jan", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil", "Août", "Sep", "Oct", "Nov", "Déc"};
        if (month >= 1 && month <= 12) {
            return monthNames[month - 1];
        }
        return "Inconnu";
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
        return "Statistiques mensuelles des dons";
    }
}
