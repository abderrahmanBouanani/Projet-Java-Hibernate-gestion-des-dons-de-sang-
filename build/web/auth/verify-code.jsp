<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Réinitialisation du mot de passe</title>
    <style>/* Même style que login.jsp */</style>
</head>
<body>
    <fieldset>
        <legend>Nouveau mot de passe</legend>
        <form action="ResetPasswordController" method="POST">
            <table>
                <tr>
                    <td>Nouveau mot de passe:</td>
                    <td><input type="password" name="newPassword" required></td>
                </tr>
                <tr>
                    <td>Confirmer mot de passe:</td>
                    <td><input type="password" name="confirmPassword" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enregistrer"></td>
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