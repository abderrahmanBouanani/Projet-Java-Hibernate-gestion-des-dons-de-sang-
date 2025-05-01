<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mise à jour du mot de passe - Gestion des Dons de Sang</title>

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
                <h2><i class="bi bi-key"></i> Nouveau mot de passe</h2>
            </div>
            <div class="auth-body">
                <form action="${pageContext.request.contextPath}/UpdatePasswordController" method="post" class="auth-form">
                    <p class="text-center mb-4">Veuillez saisir votre nouveau mot de passe</p>
                    
                    <div class="form-group">
                        <label for="password">Nouveau mot de passe</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-lock"></i></span>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="passwordcnf">Confirmer le mot de passe</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                            <input type="password" class="form-control" id="passwordcnf" name="passwordcnf" required>
                        </div>
                    </div>
                    
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-check-circle"></i> Mettre à jour
                        </button>
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
