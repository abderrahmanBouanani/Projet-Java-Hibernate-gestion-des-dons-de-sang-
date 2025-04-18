<%-- 
    Document   : page
    Created on : Apr 18, 2025, 12:49:15 PM
    Author     : admin
--%>

<%@page import="services.DonneurService"%>
<%@page import="entities.Donneur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion des Donneurs</title>
        <style>
            table { border-collapse: collapse; width: 100%; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
        </style>
    </head>
    <body>
        <h1>Gestion des Donneurs de Sang</h1>

        <fieldset>
            <legend>Formulaire Donneur</legend>
            <form method="POST" action="../DonneurController">
                <input type="hidden" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>" />
                <table>
                    <tr>
                        <td>Nom:</td>
                        <td><input type="text" name="name" value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>" required></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="email" name="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" required></td>
                    </tr>
                    <tr>
                        <td>Mot de passe:</td>
                        <td><input type="password" name="password" value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>" required></td>
                    </tr>
                    <tr>
                        <td>Groupe Sanguin:</td>
                        <td>
                            <select name="groupeSanguin" required>
                                <option value="">Sélectionner</option>
                                <option value="A+" <%= "A+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>A+</option>
                                <option value="A-" <%= "A-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>A-</option>
                                <option value="B+" <%= "B+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>B+</option>
                                <option value="B-" <%= "B-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>B-</option>
                                <option value="AB+" <%= "AB+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>AB+</option>
                                <option value="AB-" <%= "AB-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>AB-</option>
                                <option value="O+" <%= "O+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>O+</option>
                                <option value="O-" <%= "O-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>O-</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Enregistrer"></td>
                    </tr>
                </table>
            </form>
        </fieldset>

        <fieldset>
            <legend>Liste des Donneurs</legend>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Email</th>
                        <th>Groupe Sanguin</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        DonneurService ds = new DonneurService();
                        for (Donneur d : ds.findAll()) {
                    %>
                    <tr>
                        <td><%= d.getIdUser() %></td>
                        <td><%= d.getName() %></td>
                        <td><%= d.getEmail() %></td>
                        <td><%= d.getGroupeSanguin() %></td>
                        <td>
                            <a href="../DonneurController?id=<%= d.getIdUser() %>&op=delete" 
                               onclick="return confirm('Voulez-vous vraiment supprimer ce donneur?')">Supprimer</a>
                            <a href="../DonneurController?id=<%= d.getIdUser() %>&op=update">Modifier</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </fieldset>

        <fieldset>
            <legend>Recherche Avancée</legend>
            <form method="GET" action="recherche.jsp">
                <h3>Recherche par groupe sanguin</h3>
                <select name="groupeSanguin">
                    <option value="A+">A+</option>
                    <option value="A-">A-</option>
                    <option value="B+">B+</option>
                    <option value="B-">B-</option>
                    <option value="AB+">AB+</option>
                    <option value="AB-">AB-</option>
                    <option value="O+">O+</option>
                    <option value="O-">O-</option>
                </select>
                <input type="submit" value="Rechercher">
            </form>
        </fieldset>
    </body>
</html>