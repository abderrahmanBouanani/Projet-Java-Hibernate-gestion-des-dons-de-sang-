<%@page import="entities.Donneur"%>
<%@page import="services.DonService"%>
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
    <%
       Donneur donneur = (Donneur) session.getAttribute("donneur");
       if(donneur == null){
           RequestDispatcher dispatcher = request.getRequestDispatcher("RouteController?page=login"); 
           dispatcher.forward(request, response); 
       }
    %>
</head>
<body>
    <h1>Historique de mes dons</h1>
    
    <%  %>
    

    <%
        // Récupérer la liste des dons
       
        DonService ds = new DonService();
        List<Don> dons = ds.getDonsByDonneur(donneur.getIdUser());
        

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
                        <td><%= d.getId().getLieuDon() %></td>
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
    
    <div style="text-align:center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/deconnexionController">Déconnexion</a> <!-- Modifié par v0 -->
    </div>
</body>
</html>
