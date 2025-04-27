<%@ page import="java.util.List" %>
<%@ page import="entities.Don" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mon Profil - Historique des dons</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #999;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        h1 {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <h1>Historique de mes dons</h1>

    <%
        // Récupérer la liste des dons
        List<Don> dons = (List<Don>) request.getAttribute("dons");

        // Format de la date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (dons != null && !dons.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>Centre</th>
                    <th>Date</th>
                    <th>Adresse</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Afficher chaque don
                    for (Don d : dons) {
                %>
                    <tr>
                        <td><%= d.getCentreDon().getNameCentre() %></td>
                        <td><%= sdf.format(d.getId().getDateDon()) %></td>
                        <td><%= sdf.format(d.getId().getLieuDon()) %></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p style="text-align:center;">Aucun don trouvé.</p>
    <%
        }
    %>
</body>
</html>