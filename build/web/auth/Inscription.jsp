<%@page import="entities.Admin"%>
<%@page import="entities.User"%>
<%@page import="services.UserService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Formulaire d'inscription</title>
        
    </head>
    <body>
        <fieldset>
            <legend>Inscrivez-vous</legend>
            <form method="post" action="${pageContext.request.contextPath}/InscriptionController"> <!-- Modifié par v0 -->
                <input type="hidden" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>" />
                <table>
                    <tr class="form-group">
                        <td><label for="nom">Nom :</label></td>
                        <td><input type="text" id="nom" name="nom" value="<%= request.getParameter("nom") != null ? request.getParameter("nom") : "" %>" placeholder="Entrez votre nom" /></td>
                    </tr>
                    
                     <tr class="form-group">
                        <td><label for="groupeSanguin">Groupe Sanguin :</label></td>
                        <td><input type="text" id="groupeSanguin" name="groupeSanguin" value="<%= request.getParameter("groupeSanguin") != null ? request.getParameter("groupeSanguin") : "" %>" placeholder="Entrez votre groupe sanguin" /></td>  
                    </tr>
                    
                    <tr class="form-group">
                        <td><label for="email">Email :</label></td>
                        <td><input type="text" id="email" name="email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>" placeholder="Entrez votre email" /></td>  
                    </tr>
                    
                    <tr class="form-group">
                        <td><label for="mdp">Mot de passe :</label></td>
                        <td><input type="password" id="mdp" name="mdp" value="<%= request.getParameter("mdp") != null ? request.getParameter("mdp") : "" %>" placeholder="Entrez votre mot de passe" /></td>  
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Enregistrer" /></td>
                    </tr>
                </table>
            </form>
        </fieldset>
    </body>
</html>
