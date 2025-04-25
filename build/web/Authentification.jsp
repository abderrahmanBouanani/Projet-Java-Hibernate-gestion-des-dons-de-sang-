<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Connexion</title>
    </head>
    <body>

        <div class="login-container">
            <div class="login-left">
                <h2>Bienvenue</h2>
            </div>
            <div class="login-right">
                <h4 class="mb-4 text-center">Connexion</h4>
                <form action="/gestionDesDonsDeSang/AuthentificationController" method="post">
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
                        <button type="button" class="btn btn-outline-secondary">
                            <a href="forgotPassword.jsp" class="text-center" style="color: black;">Mot de passe oublié ?</a>

                        </button>
                    </div>

                    <div class="text-center small-text">
                        Vous n'avez pas de compte ?
                        <a href="Inscription.jsp">Créer un compte</a>
                    </div>

                    <% if (request.getParameter("msg") != null) {%>
                    <div class="mt-3 text-danger text-center">
                        <%= request.getParameter("msg")%>
                    </div>
                    <% }%>
                </form>
            </div>
        </div>

    </body>
</html>
