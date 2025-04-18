<%-- 
    Document   : updatemotdepasse
    Created on : Apr 18, 2025, 4:17:27 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mettre à jour le mot de passe</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; }
        .container { width: 400px; margin: 50px auto; padding: 20px; background: white; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        h2 { color: #d32f2f; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="password"] { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
        button { 
            background-color: #d32f2f; color: white; padding: 10px 15px; 
            border: none; border-radius: 4px; cursor: pointer; width: 100%; 
        }
        button:hover { background-color: #b71c1c; }
        .error { color: red; text-align: center; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Changer le mot de passe</h2>
        <% if(request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <form action="UpdatePasswordController" method="POST">
            <input type="hidden" name="token" value="<%= request.getParameter("token") %>">
            <div class="form-group">
                <label>Nouveau mot de passe:</label>
                <input type="password" name="newPassword" required>
            </div>
            <div class="form-group">
                <label>Confirmer le nouveau mot de passe:</label>
                <input type="password" name="confirmPassword" required>
            </div>
            <button type="submit">Mettre à jour</button>
        </form>
    </div>
</body>
</html>