<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bienvenue - Gestion des Dons de Sang</title>
        
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        
        <style>
            .hero-section {
                background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)), url('https://images.unsplash.com/photo-1615461066841-6116e61058f4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80');
                background-size: cover;
                background-position: center;
                color: white;
                padding: 100px 0;
                text-align: center;
            }
            
            .feature-card {
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                padding: 30px;
                margin-bottom: 30px;
                transition: transform 0.3s;
                height: 100%;
            }
            
            .feature-card:hover {
                transform: translateY(-10px);
            }
            
            .feature-icon {
                font-size: 3rem;
                margin-bottom: 20px;
                color: #e74c3c;
            }
            
            .cta-section {
                background-color: #e74c3c;
                color: white;
                padding: 60px 0;
                text-align: center;
            }
            
            .footer {
                background-color: #343a40;
                color: white;
                padding: 40px 0;
            }
            
            .footer a {
                color: white;
                text-decoration: none;
            }
            
            .footer a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <i class="bi bi-droplet-fill"></i> GestionSang
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Accueil</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#about">À propos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#features">Fonctionnalités</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#contact">Contact</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-danger ms-2 text-white" href="${pageContext.request.contextPath}/RouteController?page=login">
                                <i class="bi bi-box-arrow-in-right"></i> Connexion
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <!-- Hero Section -->
        <section class="hero-section">
            <div class="container">
                <h1 class="display-4 mb-4">Donnez du sang, sauvez des vies</h1>
                <p class="lead mb-5">Notre plateforme de gestion des dons de sang facilite le processus de don et aide à sauver plus de vies.</p>
                <div class="d-flex justify-content-center gap-3">
                    <a href="${pageContext.request.contextPath}/RouteController?page=login" class="btn btn-danger btn-lg">
                        <i class="bi bi-box-arrow-in-right"></i> Se connecter
                    </a>
                    <a href="${pageContext.request.contextPath}/RouteController?page=inscription" class="btn btn-outline-light btn-lg">
                        <i class="bi bi-person-plus"></i> S'inscrire
                    </a>
                </div>
            </div>
        </section>
        
        <!-- About Section -->
        <section class="py-5" id="about">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h2 class="mb-4">À propos de notre plateforme</h2>
                        <p class="lead">Notre système de gestion des dons de sang est conçu pour faciliter le processus de don et améliorer l'efficacité des centres de collecte.</p>
                        <p>Nous aidons à connecter les donneurs avec les centres de don, à suivre l'historique des dons et à gérer efficacement les stocks de sang. Notre objectif est de contribuer à sauver plus de vies en facilitant le processus de don de sang.</p>
                        <p>Chaque don peut sauver jusqu'à trois vies. Rejoignez-nous dans cette noble cause!</p>
                    </div>
                    <div class="col-md-6">
                        <img src="https://images.unsplash.com/photo-1579154204601-01588f351e67?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80" 
                             alt="Don de sang" class="img-fluid rounded shadow">
                    </div>
                </div>
            </div>
        </section>
        
        <!-- Features Section -->
        <section class="py-5 bg-light" id="features">
            <div class="container">
                <h2 class="text-center mb-5">Nos fonctionnalités</h2>
                <div class="row">
                    <div class="col-md-4">
                        <div class="feature-card bg-white text-center">
                            <div class="feature-icon">
                                <i class="bi bi-calendar-check"></i>
                            </div>
                            <h3>Gestion des rendez-vous</h3>
                            <p>Planifiez facilement vos dons de sang et recevez des rappels automatiques.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="feature-card bg-white text-center">
                            <div class="feature-icon">
                                <i class="bi bi-clock-history"></i>
                            </div>
                            <h3>Historique des dons</h3>
                            <p>Suivez votre historique de dons et visualisez votre contribution à la communauté.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="feature-card bg-white text-center">
                            <div class="feature-icon">
                                <i class="bi bi-graph-up"></i>
                            </div>
                            <h3>Statistiques et rapports</h3>
                            <p>Accédez à des statistiques détaillées et des rapports sur les dons de sang.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- CTA Section -->
        <section class="cta-section">
            <div class="container">
                <h2 class="mb-4">Prêt à sauver des vies?</h2>
                <p class="lead mb-4">Rejoignez notre plateforme dès aujourd'hui et commencez à faire la différence.</p>
                <a href="${pageContext.request.contextPath}/RouteController?page=inscription" class="btn btn-light btn-lg">
                    <i class="bi bi-person-plus"></i> S'inscrire maintenant
                </a>
            </div>
        </section>
        
        <!-- Contact Section -->
        <section class="py-5" id="contact">
            <div class="container">
                <h2 class="text-center mb-5">Contactez-nous</h2>
                <div class="row">
                    <div class="col-md-6">
                        <form>
                            <div class="mb-3">
                                <label for="name" class="form-label">Nom</label>
                                <input type="text" class="form-control" id="name" placeholder="Votre nom">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" placeholder="Votre email">
                            </div>
                            <div class="mb-3">
                                <label for="message" class="form-label">Message</label>
                                <textarea class="form-control" id="message" rows="5" placeholder="Votre message"></textarea>
                            </div>
                            <button type="submit" class="btn btn-danger">Envoyer</button>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <div class="card h-100">
                            <div class="card-body">
                                <h3 class="card-title">Informations de contact</h3>
                                <p class="card-text">N'hésitez pas à nous contacter pour toute question ou suggestion.</p>
                                <ul class="list-unstyled">
                                    <li class="mb-2"><i class="bi bi-geo-alt me-2"></i> 123 Rue du Don, Casablanca, Maroc</li>
                                    <li class="mb-2"><i class="bi bi-telephone me-2"></i> +212 5XX-XXXXXX</li>
                                    <li class="mb-2"><i class="bi bi-envelope me-2"></i> contact@gestionsang.ma</li>
                                </ul>
                                <div class="mt-4">
                                    <a href="#" class="me-3 text-dark"><i class="bi bi-facebook fs-4"></i></a>
                                    <a href="#" class="me-3 text-dark"><i class="bi bi-twitter fs-4"></i></a>
                                    <a href="#" class="me-3 text-dark"><i class="bi bi-instagram fs-4"></i></a>
                                    <a href="#" class="text-dark"><i class="bi bi-linkedin fs-4"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- Footer -->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <h5><i class="bi bi-droplet-fill"></i> GestionSang</h5>
                        <p>Plateforme de gestion des dons de sang pour sauver des vies.</p>
                    </div>
                    <div class="col-md-4">
                        <h5>Liens rapides</h5>
                        <ul class="list-unstyled">
                            <li><a href="#">Accueil</a></li>
                            <li><a href="#about">À propos</a></li>
                            <li><a href="#features">Fonctionnalités</a></li>
                            <li><a href="#contact">Contact</a></li>
                        </ul>
                    </div>
                    <div class="col-md-4">
                        <h5>Légal</h5>
                        <ul class="list-unstyled">
                            <li><a href="#">Conditions d'utilisation</a></li>
                            <li><a href="#">Politique de confidentialité</a></li>
                            <li><a href="#">Mentions légales</a></li>
                        </ul>
                    </div>
                </div>
                <hr class="mt-4 mb-4">
                <div class="text-center">
                    <p>&copy; 2023 GestionSang. Tous droits réservés.</p>
                </div>
            </div>
        </footer>
        
        <!-- Bootstrap JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
