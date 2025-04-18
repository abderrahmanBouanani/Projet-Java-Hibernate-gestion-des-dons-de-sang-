package controllers;

import entities.Don;
import entities.Donneur;
import entities.CentreDon;
import entities.DonPK;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DonService;
import services.DonneurService;
import services.CentreDonService;

@WebServlet(name = "DonController", urlPatterns = {"/DonController"})
public class DonController extends HttpServlet {

    private DonService ds;
    private DonneurService donneurService;
    private CentreDonService centreService;

    @Override
    public void init() throws ServletException {
        super.init();
        ds = new DonService();
        donneurService = new DonneurService();
        centreService = new CentreDonService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String op = request.getParameter("op");
        if (op == null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDon = sdf.parse(request.getParameter("dateDon"));
                String lieuDon = request.getParameter("lieuDon");
                int donneurId = Integer.parseInt(request.getParameter("donneurId"));
                int centreId = Integer.parseInt(request.getParameter("centreId"));
                
                Donneur donneur = donneurService.findById(donneurId);
                CentreDon centre = centreService.findById(centreId);
                
                Don don = new Don(dateDon, lieuDon, donneur, centre);
                ds.create(don);
                
                response.sendRedirect("dons/page.jsp");
            } catch (ParseException e) {
                throw new ServletException("Format de date invalide", e);
            }
        } else if (op.equals("delete")) {
            try {
                // Récupérez tous les paramètres de la clé composite
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDon = sdf.parse(request.getParameter("dateDon"));
                String lieuDon = request.getParameter("lieuDon");
                int donneurId = Integer.parseInt(request.getParameter("donneurId"));
                int centreId = Integer.parseInt(request.getParameter("centreId"));
                
                // Créez la clé composite
                Donneur donneur = new Donneur();
                donneur.setIdUser(donneurId);
                
                CentreDon centre = new CentreDon();
                centre.setIdCentre(centreId);
                
                DonPK pk = new DonPK(dateDon, lieuDon, donneur, centre);
                
                // Trouvez le don complet
                Don don = ds.findByIdPk(pk);
                
                if (don != null) {
                    ds.delete(don);
                }
                
                response.sendRedirect("dons/page.jsp");
            } catch (Exception e) {
                throw new ServletException("Erreur lors de la suppression", e);
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
        return "Don Controller";
    }
}