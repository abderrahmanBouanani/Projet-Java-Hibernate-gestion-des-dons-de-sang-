<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.title} - Gestion des Dons de Sang</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    
    <!-- Page specific CSS if needed -->
    <% if (request.getParameter("specificCss") != null) { %>
        <link href="${pageContext.request.contextPath}/css/<%= request.getParameter("specificCss") %>" rel="stylesheet">
    <% } %>
</head>
<body>
    <div class="main-container">
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="sidebar-header">
                <h3><i class="bi bi-droplet-fill"></i> GestionSang</h3>
            </div>
            
            <ul class="sidebar-menu">
                <% 
                    String currentPage = request.getParameter("currentPage");
                    boolean isAdmin = session.getAttribute("admin") != null;
                    boolean isDonneur = session.getAttribute("donneur") != null;
                    boolean isLoggedIn = isAdmin || isDonneur;
                    boolean readOnlyMode = request.getAttribute("readOnlyMode") != null && (Boolean)request.getAttribute("readOnlyMode");
                %>
                
                <% if (isAdmin) { %>
                    <li class="<%= "dashboard".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=users">
                            <i class="bi bi-speedometer2"></i> Tableau de bord
                        </a>
                    </li>
                    <li class="<%= "users".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=users">
                            <i class="bi bi-people-fill"></i> Gestion des Donneurs
                        </a>
                    </li>
                    <li class="<%= "dons".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=dons">
                            <i class="bi bi-droplet"></i> Gestion des Dons
                        </a>
                    </li>
                    <li class="<%= "donHistory".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=donHistory">
                            <i class="bi bi-clock-history"></i> Historique des Dons
                        </a>
                    </li>
                    <li class="<%= "donGraph".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=donGraph">
                            <i class="bi bi-bar-chart-fill"></i> Statistiques
                        </a>
                    </li>
                <% } else { %>
                    <li class="<%= "profil".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=profil">
                            <i class="bi bi-person-circle"></i> Profil
                        </a>
                    </li>
                    <li class="<%= "donHistory".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=donHistory">
                            <i class="bi bi-clock-history"></i> Historique des Dons
                        </a>
                    </li>
                    <!-- Suppression du lien vers les statistiques pour les utilisateurs non-administrateurs -->
                <% } %>
                
                <% if (isLoggedIn) { %>
                    <li>
                        <a href="${pageContext.request.contextPath}/deconnexionController">
                            <i class="bi bi-box-arrow-right"></i> Déconnexion
                        </a>
                    </li>
                <% } else { %>
                    <li class="<%= "login".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=login">
                            <i class="bi bi-box-arrow-in-right"></i> Connexion
                        </a>
                    </li>
                    <li class="<%= "inscription".equals(currentPage) ? "active" : "" %>">
                        <a href="${pageContext.request.contextPath}/RouteController?page=inscription">
                            <i class="bi bi-person-plus"></i> Inscription
                        </a>
                    </li>
                <% } %>
            </ul>
        </div>
        
        <!-- Contenu principal -->
        <div class="content">
            <div class="main-header">
                <h1>${param.pageTitle}</h1>
                <div>
                    <% if (isAdmin) { %>
                        <span class="badge bg-primary">Administrateur</span>
                    <% } else if (isDonneur) { %>
                        <span class="badge bg-success">Donneur</span>
                    <% } else { %>
                        <span class="badge bg-info">Mode Visiteur</span>
                    <% } %>
                </div>
            </div>
            
            <!-- Bannière mode lecture seule -->
            <% if (request.getAttribute("readOnlyMode") != null && (Boolean)request.getAttribute("readOnlyMode")) { %>
                <div class="read-only-banner">
                    <div>
                        <i class="bi bi-eye"></i> Vous êtes en mode lecture seule. Certaines fonctionnalités sont limitées.
                    </div>
                    <a href="${pageContext.request.contextPath}/RouteController?page=login" class="btn">Se connecter</a>
                </div>
            <% } %>
            
            <!-- Affichage des messages d'erreur et de succès -->
            <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger">
                    <i class="bi bi-exclamation-triangle-fill"></i> <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <% if (request.getAttribute("successMessage") != null) { %>
                <div class="alert alert-success">
                    <i class="bi bi-check-circle-fill"></i> <%= request.getAttribute("successMessage") %>
                </div>
            <% } %>
            
            <% if (request.getAttribute("info") != null) { %>
                <div class="alert alert-info">
                    <i class="bi bi-info-circle-fill"></i> <%= request.getAttribute("info") %>
                </div>
            <% } %>
            
            <div class="container-fluid">
