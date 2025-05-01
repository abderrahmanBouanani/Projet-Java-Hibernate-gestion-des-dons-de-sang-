<%@page import="entities.Donneur"%>
<%@page import="services.DonService"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Don" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="title" value="Mon Profil" />
    <jsp:param name="currentPage" value="profil" />
    <jsp:param name="pageTitle" value="Mon Profil" />
</jsp:include>

<%
    Donneur donneur = (Donneur) session.getAttribute("donneur");
    if(donneur == null){
        response.sendRedirect(request.getContextPath() + "/RouteController?page=login");
        return;
    }
    
    // Récupérer la liste des dons
    DonService ds = new DonService();
    List<Don> dons = ds.getDonsByDonneur(donneur.getIdUser());
    
    // Format de la date
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>

<div class="row">
    <div class="col-md-4">
        <div class="card">
            <div class="card-header">
                <h2><i class="bi bi-person-circle"></i> Informations personnelles</h2>
            </div>
            <div class="card-body">
                <div class="text-center mb-4">
                    <div class="avatar-placeholder">
                        <i class="bi bi-person-circle" style="font-size: 5rem; color: #e74c3c;"></i>
                    </div>
                    <h3 class="mt-3"><%= donneur.getName() %></h3>
                    <span class="badge bg-danger"><%= donneur.getGroupeSanguin() %></span>
                </div>
                
                <ul class="list-group">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span><i class="bi bi-envelope"></i> Email</span>
                        <span><%= donneur.getEmail() %></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span><i class="bi bi-droplet"></i> Groupe sanguin</span>
                        <span><%= donneur.getGroupeSanguin() %></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span><i class="bi bi-gift"></i> Nombre de dons</span>
                        <span class="badge bg-primary rounded-pill"><%= dons != null ? dons.size() : 0 %></span>
                    </li>
                </ul>
                
                <div class="mt-4 text-center">
                    <a href="${pageContext.request.contextPath}/RouteController?page=modification&id=<%= donneur.getIdUser() %>" class="btn btn-outline-primary">
                        <i class="bi bi-pencil"></i> Modifier mon profil
                    </a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-md-8">
        <div class="card">
            <div class="card-header">
                <h2><i class="bi bi-clock-history"></i> Historique de mes dons</h2>
            </div>
            <div class="card-body">
                <% if (dons != null && !dons.isEmpty()) { %>
                    <div class="table-container">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th><i class="bi bi-hospital"></i> Centre</th>
                                    <th><i class="bi bi-calendar-date"></i> Date</th>
                                    <th><i class="bi bi-geo-alt"></i> Adresse</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Don d : dons) { %>
                                    <tr>
                                        <td><%= d.getCentreDon().getNameCentre() %></td>
                                        <td><%= sdf.format(d.getId().getDateDon()) %></td>
                                        <td><%= d.getId().getLieuDon() %></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                <% } else { %>
                    <div class="alert alert-info text-center">
                        <i class="bi bi-info-circle"></i> Vous n'avez pas encore effectué de don de sang.
                    </div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
