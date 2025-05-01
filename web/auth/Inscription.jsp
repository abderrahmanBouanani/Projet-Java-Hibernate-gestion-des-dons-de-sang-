<%@page import="entities.Admin"%>
<%@page import="entities.User"%>
<%@page import="services.UserService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inscription - Gestion des Dons de Sang</title>
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/auth.css" rel="stylesheet">
        
        <%
            // Empêcher la mise en cache
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        %>
    </head>
    <body>
        <div class="auth-container">
            <div class="auth-header">
                <h2><i class="bi bi-person-plus"></i> Inscription</h2>
            </div>
            <div class="auth-body">
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
                
                <form method="post" action="${pageContext.request.contextPath}/InscriptionController" class="auth-form">
                    <input type="hidden" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>" />
                    
                    <div class="form-group">
                        <label for="nom">Nom</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-person"></i></span>
                            <input type="text" class="form-control" id="nom" name="nom" 
                                   value="<%= request.getAttribute("nom") != null ? request.getAttribute("nom") : (request.getParameter("nom") != null ? request.getParameter("nom") : "") %>" 
                                   placeholder="Entrez votre nom" required />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="groupeSanguin">Groupe Sanguin</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-droplet"></i></span>
                            <select class="form-control" id="groupeSanguin" name="groupeSanguin" required>
                                <option value="" disabled selected>Sélectionnez votre groupe sanguin</option>
                                <option value="A+" <%= "A+".equals(request.getAttribute("groupeSanguin")) || "A+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>A+</option>
                                <option value="A-" <%= "A-".equals(request.getAttribute("groupeSanguin")) || "A-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>A-</option>
                                <option value="B+" <%= "B+".equals(request.getAttribute("groupeSanguin")) || "B+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>B+</option>
                                <option value="B-" <%= "B-".equals(request.getAttribute("groupeSanguin")) || "B-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>B-</option>
                                <option value="AB+" <%= "AB+".equals(request.getAttribute("groupeSanguin")) || "AB+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>AB+</option>
                                <option value="AB-" <%= "AB-".equals(request.getAttribute("groupeSanguin")) || "AB-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>AB-</option>
                                <option value="O+" <%= "O+".equals(request.getAttribute("groupeSanguin")) || "O+".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>O+</option>
                                <option value="O-" <%= "O-".equals(request.getAttribute("groupeSanguin")) || "O-".equals(request.getParameter("groupeSanguin")) ? "selected" : "" %>>O-</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                            <input type="email" class="form-control" id="email" name="email" 
                                   value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : (request.getParameter("email") != null ? request.getParameter("email") : "") %>" 
                                   placeholder="Entrez votre email" required />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="mdp">Mot de passe</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-lock"></i></span>
                            <input type="password" class="form-control" id="mdp" name="mdp" 
                                   value="<%= request.getParameter("mdp") != null ? request.getParameter("mdp") : "" %>" 
                                   placeholder="Entrez votre mot de passe (min. 6 caractères)" required />
                        </div>
                        <small class="text-muted">Le mot de passe doit contenir au moins 6 caractères</small>
                    </div>
                    
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-person-check"></i> S'inscrire
                        </button>
                    </div>
                    
                    <div class="text-center small-text mt-3">
                        Vous avez déjà un compte ? 
                        <a href="${pageContext.request.contextPath}/RouteController?page=login">Se connecter</a>
                    </div>
                </form>
            </div>
        </div>
        
        <!-- Bootstrap JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
