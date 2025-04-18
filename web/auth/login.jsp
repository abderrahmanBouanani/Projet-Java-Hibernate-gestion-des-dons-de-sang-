<%-- 
    Document   : Authentification
    Created on : Apr 18, 2025, 4:18:02 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Authentification</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        fieldset { width: 400px; padding: 20px; }
        table { width: 100%; }
        .error { color: red; }
    </style>
</head>
<body>
    <fieldset>
        <legend>Authentification</legend>
        <form action="AuthController" method="POST">
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" value="${param.email}" required></td>
                </tr>
                <tr>
                    <td>Mot de passe:</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Se connecter"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="auth/forgot-password.jsp">Mot de passe oublié?</a> | 
                        <a href="auth/register.jsp">Créer un compte</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="error">
                        ${param.msg}
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</body>
</html>