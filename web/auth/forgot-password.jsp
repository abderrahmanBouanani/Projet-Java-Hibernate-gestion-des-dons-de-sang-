<%-- 
    Document   : Mpob
    Created on : Apr 18, 2025, 4:17:39 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mot de passe oublié</title>
    <style>/* Même style que login.jsp */</style>
</head>
<body>
    <fieldset>
        <legend>Réinitialisation du mot de passe</legend>
        <form action="PasswordResetController" method="POST">
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Envoyer le code"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="auth/login.jsp">Retour à la connexion</a>
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