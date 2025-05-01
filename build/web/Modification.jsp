<%@page import="entities.Donneur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="title" value="Modification du profil" />
    <jsp:param name="currentPage" value="modification" />
    <jsp:param name="pageTitle" value="Modification du profil" />
</jsp:include>

<%
    Donneur donneur = (Donneur) request.getAttribute("donneur");
    if (donneur == null) {
        donneur = (Donneur) session.getAttribute("donneur");
    }
    
    if (donneur == null) {
        response.sendRedirect(request.getContextPath() + "/RouteController?page=login");
        return;
    }
%>

<div class="row">
    <div class="col-md-6 mx-auto">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-pencil-square"></i> Modifier vos informations</h3>
            </div>
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/ModificationController">
                    <input type="hidden" name="id" value="<%= donneur.getIdUser() %>" />
                    
                    <div class="form-group mb-3">
                        <label for="nom" class="form-label">Nom</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-person"></i></span>
                            <input type="text" class="form-control" id="nom" name="nom" 
                                   value="<%= donneur.getName() %>" 
                                   placeholder="Entrez votre nom" required />
                        </div>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="groupeSanguin" class="form-label">Groupe Sanguin</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-droplet"></i></span>
                            <select class="form-control" id="groupeSanguin" name="groupeSanguin" required>
                                <option value="" disabled>Sélectionnez votre groupe sanguin</option>
                                <option value="A+" <%= "A+".equals(donneur.getGroupeSanguin()) ? "selected" : "" %>>A+</option>
                                <option value="A-" <%= "A-".equals(donneur.getGroupeSanguin()) ? "selected" : "" %>>A-</option>
                                <option value="B+" <%= "B+".equals(donneur.getGroupeSanguin()) ? "selected" : "" %>>B+</option>
                                <option value="B-" <%= "B-".equals(donneur.getGroupeSanguin()) ? "selected" : "" %>>B-</option>
                                <option value="AB+" <%= "AB+".equals(donneur.getGroupeSanguin()) ? "selected" : "" %>>AB+</option>
                                <option value="AB-" <%= "AB-".equals(donneur.getGroupeSanguin()) ? "selected" : "" %>>AB-</option>
                                <option value="O+" <%= "O+".equals(donneur.getGroupeSanguin()) ? "selected" : "" %>>O+</option>
                                <option value="O-" <%= "O-".equals(donneur.getGroupeSanguin()) ? "selected" : "" %>>O-</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="email" class="form-label">Email</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                            <input type="email" class="form-control" id="email" name="email" 
                                   value="<%= donneur.getEmail() %>" 
                                   placeholder="Entrez votre email" required />
                        </div>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="mdp" class="form-label">Mot de passe</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-lock"></i></span>
                            <input type="password" class="form-control" id="mdp" name="mdp" 
                                   value="<%= donneur.getMotDePasse() %>" 
                                   placeholder="Entrez votre mot de passe" required />
                        </div>
                    </div>
                    
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save"></i> Enregistrer les modifications
                        </button>
                        <a href="javascript:history.back()" class="btn btn-secondary ms-2">
                            <i class="bi bi-x-circle"></i> Annuler
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
