<%@page import="entities.User"%>
<%@page import="services.UserService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Liste des Clients</title>
    </head>
    <body>
        <div class="sidebar">
            <h2>Admin Dashboard</h2>
        </div>


        <div class="main-content">
            <h1>Liste des donneurs</h1>

            <fieldset>
                <legend>Liste des donneurs</legend>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Email</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            UserService us = new UserService();
                            java.util.List<User> users = us.findAll();
                            if (users != null && !users.isEmpty()) {
                                for (User u : users) {
                        %>
                        <tr>
                            <td><%= u.getIdUser()%></td>
                            <td><%= u.getName()%></td>
                            <td><%= u.getEmail()%></td>
                            <td class="actions-container">
                                <a href="<%= request.getContextPath()%>/UserController?id=<%= u.getIdUser()%>&op=delete">Supprimer</a>
                                <a href="<%= request.getContextPath()%>/UserController?id=<%= u.getIdUser()%>&op=update">Modifier</a>

                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="5" class="empty-message">Aucun donneur trouvé</td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </fieldset>

            <a href="Inscription.jsp" class="add-button">Ajouter un donneur</a>
            <a href="donHistory.jsp" class="add-button">Historique des donns</a>
        </div>
    </body>
</html>
