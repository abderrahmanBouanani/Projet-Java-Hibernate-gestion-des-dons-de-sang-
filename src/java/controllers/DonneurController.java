package controllers;

import entities.Donneur;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DonneurService;

@WebServlet(name = "DonneurController", urlPatterns = {"/DonneurController"})
public class DonneurController extends HttpServlet {

    private DonneurService ds;

    @Override
    public void init() throws ServletException {
        super.init();
        ds = new DonneurService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String op = request.getParameter("op");
        if (op == null) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String groupeSanguin = request.getParameter("groupeSanguin");
            
            if (id == null || id.isEmpty()) {
                Donneur d = new Donneur(name, email, password, groupeSanguin);
                ds.create(d);
            } else {
                Donneur d = new Donneur(name, email, password, groupeSanguin);
                d.setIdUser(Integer.parseInt(id));
                ds.update(d);
            }
            response.sendRedirect("donneurs/page.jsp");
        } else if (op.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            ds.delete(ds.findById(id));
            response.sendRedirect("donneurs/page.jsp");
        } else if (op.equals("update")) {
            Donneur d = ds.findById(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("donneurs/page.jsp?id=" + d.getIdUser() + 
                    "&name=" + d.getName() + 
                    "&email=" + d.getEmail() + 
                    "&password=" + d.getMotDePasse() + 
                    "&groupeSanguin=" + d.getGroupeSanguin());
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
        return "Donneur Controller";
    }
}