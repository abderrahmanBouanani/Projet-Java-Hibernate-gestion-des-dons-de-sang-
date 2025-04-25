/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import entities.Donneur;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.DonneurService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "InscriptionController", urlPatterns = {"/InscriptionController"})
public class InscriptionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DonneurService ds = new DonneurService();

        String op = request.getParameter("op");
        if (op == null) {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                String nom = request.getParameter("nom");
                String email = request.getParameter("email");
                String password = request.getParameter("mdp");
                String groupeSanguin = request.getParameter("groupeSanguin");
                ds.create(new Donneur(nom, email, password, groupeSanguin));
                response.sendRedirect("Authentification.jsp");
            } else {
                String nom = request.getParameter("nom");
                String email = request.getParameter("email");
                String password = request.getParameter("mdp");
                String groupeSanguin = request.getParameter("groupeSanguin");                
                Donneur u = new Donneur(nom, email, password, groupeSanguin);
                u.setIdUser(Integer.parseInt(id));
                ds.update(u);
                response.sendRedirect("users.jsp");
            }
        } else if (op.equals("delete")) {
            String id = request.getParameter("id");
            ds.delete(ds.findById(Integer.parseInt(id)));
            response.sendRedirect("users.jsp");
        } else if (op.equals("update")) {
            String id = request.getParameter("id");
            Donneur u = ds.findById(Integer.parseInt(id));
            response.sendRedirect("Authentification.jsp?id=" + u.getIdUser()+ "&nom=" + u.getName() + "&email=" + u.getEmail() + "&mdp=" + u.getMotDePasse());
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
        return "Short description";
    }

}
