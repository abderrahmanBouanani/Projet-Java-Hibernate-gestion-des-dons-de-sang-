<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vérification - Gestion des Dons de Sang</title>

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
                <h2><i class="bi bi-shield-check"></i> Vérification</h2>
            </div>
            <div class="auth-body">
                <form action="${pageContext.request.contextPath}/Verfier" method="post" class="auth-form">
                    <p class="text-center mb-4">Saisissez le code de vérification envoyé à votre adresse e-mail</p>
                    
                    <div class="form-group">
                        <label for="code">Code de vérification</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-123"></i></span>
                            <input type="number" class="form-control" id="code" name="code" required>
                        </div>
                    </div>
                    
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-check-circle"></i> Valider
                        </button>
                    </div>
                    
                    <% if (request.getParameter("msg") != null && !request.getParameter("msg").isEmpty()) { %>
                        <div class="alert alert-danger mt-3 text-center">
                            <%= request.getParameter("msg") %>
                        </div>
                    <% } %>
                </form>
            </div>
        </div>

        <!-- Bootstrap JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
