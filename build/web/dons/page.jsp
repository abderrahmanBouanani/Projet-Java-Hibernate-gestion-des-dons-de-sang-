<%@page import="entities.Admin"%>
<%@page import="services.DonService"%>
<%@page import="entities.Don"%>
<%@page import="entities.Donneur"%>
<%@page import="entities.CentreDon"%>
<%@page import="services.DonService"%>
<%@page import="services.DonneurService"%>
<%@page import="services.CentreDonService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="title" value="Gestion des Dons" />
    <jsp:param name="currentPage" value="dons" />
    <jsp:param name="pageTitle" value="Gestion des Dons de Sang" />
</jsp:include>

<%
    Admin admin = (Admin) session.getAttribute("admin");
    if(admin == null){
        response.sendRedirect(request.getContextPath() + "/RouteController?page=login");
        return;
    }
    
    DonneurService ds = new DonneurService();
    CentreDonService cs = new CentreDonService();
    DonService donService = new DonService();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>

<div class="row">
    <div class="col-md-4">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-plus-circle"></i> Nouveau Don</h3>
            </div>
            <div class="card-body">
                <form method="POST" action="${pageContext.request.contextPath}/DonController">
                    <div class="form-group mb-3">
                        <label for="dateDon" class="form-label">Date du don</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-calendar"></i></span>
                            <input type="date" class="form-control" id="dateDon" name="dateDon" required>
                        </div>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="lieuDon" class="form-label">Lieu du don</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-geo-alt"></i></span>
                            <input type="text" class="form-control" id="lieuDon" name="lieuDon" placeholder="Salle, étage, etc." required>
                        </div>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="donneurId" class="form-label">Donneur</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-person"></i></span>
                            <select class="form-control" id="donneurId" name="donneurId" required>
                                <option value="">Sélectionner un donneur</option>
                                <% for (Donneur d : ds.findAll()) { %>
                                    <option value="<%= d.getIdUser() %>"><%= d.getName() %> (<%= d.getGroupeSanguin() %>)</option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group mb-3">
                        <label for="centreId" class="form-label">Centre de don</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-hospital"></i></span>
                            <select class="form-control" id="centreId" name="centreId" required>
                                <option value="">Sélectionner un centre</option>
                                <% for (CentreDon c : cs.findAll()) { %>
                                    <option value="<%= c.getIdCentre() %>"><%= c.getAdresseCentre() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save"></i> Enregistrer le don
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="col-md-8">
        <div class="card">
            <div class="card-header">
                <div class="d-flex justify-content-between align-items-center">
                    <h3 class="card-title"><i class="bi bi-list-ul"></i> Liste des Dons</h3>
                    <div class="input-group" style="width: 300px;">
                        <input type="text" id="searchDons" class="form-control" placeholder="Rechercher...">
                        <span class="input-group-text"><i class="bi bi-search"></i></span>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="table-container">
                    <table class="table table-striped table-hover" id="donsTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Date</th>
                                <th>Lieu</th>
                                <th>Donneur</th>
                                <th>Groupe Sanguin</th>
                                <th>Centre</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Don d : donService.findAll()) { %>
                                <tr>
                                    <td><%= d.getId().hashCode() %></td>
                                    <td><%= sdf.format(d.getId().getDateDon()) %></td>
                                    <td><%= d.getId().getLieuDon() %></td>
                                    <td><%= d.getDonneur().getName() %></td>
                                    <td><span class="badge bg-danger"><%= d.getDonneur().getGroupeSanguin() %></span></td>
                                    <td><%= d.getCentreDon().getAdresseCentre() %></td>
                                    <td>
                                        <form method="POST" action="${pageContext.request.contextPath}/DonController">
                                            <input type="hidden" name="op" value="delete">
                                            <input type="hidden" name="dateDon" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(d.getId().getDateDon()) %>">
                                            <input type="hidden" name="lieuDon" value="<%= d.getId().getLieuDon() %>">
                                            <input type="hidden" name="donneurId" value="<%= d.getDonneur().getIdUser() %>">
                                            <input type="hidden" name="centreId" value="<%= d.getCentreDon().getIdCentre() %>">
                                            <button type="submit" class="btn btn-sm btn-outline-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce don?')">
                                                <i class="bi bi-trash"></i> Supprimer
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    // Fonction de recherche dans le tableau des dons
    document.addEventListener('DOMContentLoaded', function() {
        const searchInput = document.getElementById('searchDons');
        const table = document.getElementById('donsTable');
        const rows = table.getElementsByTagName('tr');
        
        searchInput.addEventListener('keyup', function() {
            const searchText = searchInput.value.toLowerCase();
            
            for (let i = 1; i < rows.length; i++) {
                const row = rows[i];
                const cells = row.getElementsByTagName('td');
                let found = false;
                
                for (let j = 0; j < cells.length - 1; j++) {
                    const cellText = cells[j].textContent.toLowerCase();
                    if (cellText.indexOf(searchText) > -1) {
                        found = true;
                        break;
                    }
                }
                
                if (found) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            }
        });
    });
</script>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
