<%@page import="services.DonService"%>
<%@page import="entities.Don"%>
<%@page import="entities.Donneur"%>
<%@page import="entities.CentreDon"%>
<%@page import="services.DonService"%>
<%@page import="services.DonneurService"%>
<%@page import="services.CentreDonService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion des Dons</title>
        <style>
            table { border-collapse: collapse; width: 100%; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
        </style>
    </head>
    <body>
        <h1>Gestion des Dons de Sang</h1>

        <fieldset>
            <legend>Nouveau Don</legend>
            <form method="POST" action="../DonController">
                <table>
                    <tr>
                        <td>Date:</td>
                        <td><input type="date" name="dateDon" required></td>
                    </tr>
                    <tr>
                        <td>Lieu:</td>
                        <td><input type="text" name="lieuDon" required></td>
                    </tr>
                    <tr>
                        <td>Donneur:</td>
                        <td>
                            <select name="donneurId" required>
                                <option value="">Sélectionner un donneur</option>
                                <%
                                    DonneurService ds = new DonneurService();
                                    for (Donneur d : ds.findAll()) {
                                %>
                                <option value="<%= d.getIdUser()%>"><%= d.getName()%> (<%= d.getGroupeSanguin()%>)</option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Centre:</td>
                        <td>
                            <select name="centreId" required>
                                <option value="">Sélectionner un centre</option>
                                <%
                                    CentreDonService cs = new CentreDonService();
                                    for (CentreDon c : cs.findAll()) {
                                %>
                                <option value="<%= c.getIdCentre()%>"><%= c.getAdresseCentre()%></option>
                                <% } %>
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
            <legend>Liste des Dons</legend>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Lieu</th>
                        <th>Donneur</th>
                        <th>Groupe Sanguin</th>
                        <th>Centre</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        DonService donService = new DonService();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        for (Don d : donService.findAll()) {
                    %>
                    <tr>
                        <td><%= d.getId().hashCode() %></td>
                        <td><%= sdf.format(d.getId().getDateDon()) %></td>
                        <td><%= d.getId().getLieuDon() %></td>
                        <td><%= d.getDonneur().getName() %></td>
                        <td><%= d.getDonneur().getGroupeSanguin() %></td>
                        <td><%= d.getCentreDon().getAdresseCentre() %></td>
                        <td>
                            <form method="POST" action="../DonController">
                                <input type="hidden" name="op" value="delete">
                                <input type="hidden" name="dateDon" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(d.getId().getDateDon()) %>">
                                <input type="hidden" name="lieuDon" value="<%= d.getId().getLieuDon() %>">
                                <input type="hidden" name="donneurId" value="<%= d.getDonneur().getIdUser() %>">
                                <input type="hidden" name="centreId" value="<%= d.getCentreDon().getIdCentre() %>">
                                <input type="submit" value="Supprimer" onclick="return confirm('Êtes-vous sûr?')">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </fieldset>
    </body>
</html>