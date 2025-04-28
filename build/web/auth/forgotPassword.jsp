<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mot de passe oublié</title>

        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <fieldset>
            <legend>Mot de passe oublié</legend>
            <form action="${pageContext.request.contextPath}/Mdob" method="post"> <!-- Modifié par v0 -->
                <h3>Entrez votre adresse e-mail pour recevoir le code de réinitialisation</h3>
                <table>
                    <tr>
                        <td><label for="email">E-mail</label></td>
                        <td><input type="email" id="email" name="email" required></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Envoyer"></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <h4 class="error-message">
                                <%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>
                            </h4>

                        </td>
                    </tr>
                </table>
            </form>
        </fieldset>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
