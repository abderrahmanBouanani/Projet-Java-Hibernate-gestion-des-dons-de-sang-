<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Connexion - Gestion des Dons de Sang</title>
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
        
        <%
            // Empêcher la mise en cache pour éviter le retour en arrière après déconnexion
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            
            // Détruire la session existante
            session.invalidate();
        %>
    </head>
    <body>
        <div class="login-container">
            <div class="login-left">
                <div>
                    <h2><i class="bi bi-droplet-fill"></i> Gestion des Dons de Sang</h2>
                    <p class="mt-3">Plateforme de gestion des dons de sang pour sauver des vies</p>
                </div>
            </div>
            <div class="login-right">
                <div class="auth-body">
                    <h4 class="mb-4 text-center">Connexion</h4>
                    
                    <!-- Affichage des messages d'erreur -->
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger">
                            <i class="bi bi-exclamation-triangle-fill"></i> <%= request.getAttribute("error") %>
                        </div>
                    <% } %>
                    
                    <!-- Affichage des messages de succès -->
                    <% if (request.getAttribute("successMessage") != null) { %>
                        <div class="alert alert-success">
                            <i class="bi bi-check-circle-fill"></i> <%= request.getAttribute("successMessage") %>
                        </div>
                    <% } %>
                    
                    <form action="${pageContext.request.contextPath}/AuthentificationController" method="post" class="auth-form">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                                <input type="email" class="form-control" id="email" name="email" 
                                       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" 
                                       required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Mot de passe</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-lock"></i></span>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="remember">
                            <label class="form-check-label small-text" for="remember">Se souvenir de moi</label>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-box-arrow-in-right me-1"></i>Connexion
                            </button>
                        </div>

                        <div class="divider">ou</div>

                        <div class="d-grid mb-3">
                            <a href="${pageContext.request.contextPath}/RouteController?page=forgotPassword" class="btn btn-outline-secondary">
                                Mot de passe oublié ?
                            </a>
                        </div>

                        <div class="text-center small-text">
                            Vous n'avez pas de compte ?
                            <a href="${pageContext.request.contextPath}/RouteController?page=inscription">Créer un compte</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- Bootstrap JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        
        <!-- Script pour empêcher la navigation arrière après déconnexion -->
        <script>
            window.onload = function() {
                if (window.history && window.history.pushState) {
                    window.history.pushState('forward', null, '${pageContext.request.contextPath}/RouteController?page=login');
                    window.onpopstate = function() {
                        window.history.pushState('forward', null, '${pageContext.request.contextPath}/RouteController?page=login');
                    };
                }
            }
        </script>
    </body>
</html>
