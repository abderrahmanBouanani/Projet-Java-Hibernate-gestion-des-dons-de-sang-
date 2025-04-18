<%-- 
    Document   : Inscription
    Created on : Apr 18, 2025, 4:17:51 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inscription</title>
    <style>/* Même style que login.jsp */</style>
</head>
<body>
    <fieldset>
        <legend>Inscription</legend>
        <form action="RegisterController" method="POST">
            <table>
                <tr>
                    <td>Nom:</td>
                    <td><input type="text" name="nom" required></td>
                </tr>
                <tr>
                    <td>Prénom:</td>
                    <td><input type="text" name="prenom" required></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" required></td>
                </tr>
                <tr>
                    <td>Mot de passe:</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td>Confirmer mot de passe:</td>
                    <td><input type="password" name="confirmPassword" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="S'inscrire"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="auth/login.jsp">Déjà inscrit? Se connecter</a>
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