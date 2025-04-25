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
        <title>Historique des Dons</title>
        <style>
            table { border-collapse: collapse; width: 100%; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
        </style>
    </head>
    <body>
        <h1>Historique des Dons de Sang</h1>

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
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </fieldset>
    </body>
</html>