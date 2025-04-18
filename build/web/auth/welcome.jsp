<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bienvenue</title>
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("auth/login.jsp");
            return;
        }
    %>
    <h1>Bienvenue, <%= user.getName() %>!</h1>
    <p>Vous êtes maintenant connecté à votre compte.</p>
    
    <form action="LogoutController" method="POST">
        <input type="submit" value="Déconnexion">
    </form>
</body>
</html>