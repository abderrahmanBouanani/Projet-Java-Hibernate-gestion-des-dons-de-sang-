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
            session.removeAttribute("donneur");
            session.removeAttribute("admin");
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
                    <form action="${pageContext.request.contextPath}/AuthentificationController" method="post" class="auth-form">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                                <input type="email" class="form-control" id="email" name="email" required>
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

                        <% if (request.getParameter("msg") != null) {%>
                        <div class="mt-3 text-danger text-center">
                            <%= request.getParameter("msg")%>
                        </div>
                        <% }%>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- Bootstrap JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
