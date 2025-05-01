<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mot de passe oublié - Gestion des Dons de Sang</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
    </head>
    <body>
        <div class="auth-container">
            <div class="auth-header">
                <h2><i class="bi bi-key"></i> Mot de passe oublié</h2>
            </div>
            <div class="auth-body">
                <form action="${pageContext.request.contextPath}/Mdob" method="post" class="auth-form">
                    <p class="text-center mb-4">Entrez votre adresse e-mail pour recevoir le code de réinitialisation</p>
                    
                    <div class="form-group">
                        <label for="email">E-mail</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                    </div>
                    
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-send"></i> Envoyer le code
                        </button>
                    </div>
                    
                    <div class="text-center small-text mt-3">
                        <a href="${pageContext.request.contextPath}/RouteController?page=login">
                            <i class="bi bi-arrow-left"></i> Retour à la connexion
                        </a>
                    </div>
                    
                    <% if (request.getAttribute("msg") != null) { %>
                        <div class="alert alert-danger mt-3 text-center">
                            <%= request.getAttribute("msg") %>
                        </div>
                    <% } %>
                </form>
            </div>
        </div>

        <!-- Bootstrap JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
