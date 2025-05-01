<%@page import="java.util.List"%>
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
    <jsp:param name="title" value="Historique des Dons" />
    <jsp:param name="currentPage" value="donHistory" />
    <jsp:param name="pageTitle" value="Historique des Dons de Sang" />
</jsp:include>

<%
    // Vérifier si l'utilisateur est connecté
    Admin admin = (Admin) session.getAttribute("admin");
    Donneur donneur = (Donneur) session.getAttribute("donneur");
    boolean readOnlyMode = request.getAttribute("readOnlyMode") != null && (Boolean)request.getAttribute("readOnlyMode");
    
    DonService donService = new DonService();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    // Liste des dons à afficher (tous les dons pour admin, seulement les dons du donneur pour un donneur)
    List<Don> dons = null;
    if (admin != null) {
        dons = donService.findAll();
    } else if (donneur != null) {
        dons = donService.getDonsByDonneur(donneur.getIdUser());
    } else if (readOnlyMode) {
        // En mode lecture seule, afficher tous les dons
        dons = donService.findAll();
    }
%>

<div class="row mb-4">
    <div class="col-md-12">
        <div class="card <%= readOnlyMode ? "read-only-mode" : "" %>">
            <div class="card-header">
                <div class="d-flex justify-content-between align-items-center">
                    <h3 class="card-title"><i class="bi bi-clock-history"></i> Historique complet des dons</h3>
                    <div class="input-group" style="width: 300px;">
                        <input type="text" id="searchHistory" class="form-control" placeholder="Rechercher...">
                        <span class="input-group-text"><i class="bi bi-search"></i></span>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="table-container">
                    <table class="table table-striped table-hover" id="historyTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Date</th>
                                <th>Lieu</th>
                                <th>Donneur</th>
                                <th>Groupe Sanguin</th>
                                <th>Centre</th>
                                <th>Adresse</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (dons != null && !dons.isEmpty()) { %>
                                <% for (Don d : dons) { %>
                                    <tr>
                                        <td><%= d.getId().hashCode() %></td>
                                        <td><%= sdf.format(d.getId().getDateDon()) %></td>
                                        <td><%= d.getId().getLieuDon() %></td>
                                        <td><%= d.getDonneur().getName() %></td>
                                        <td><span class="badge bg-danger"><%= d.getDonneur().getGroupeSanguin() %></span></td>
                                        <td><%= d.getCentreDon().getNameCentre() != null ? d.getCentreDon().getNameCentre() : "N/A" %></td>
                                        <td><%= d.getCentreDon().getAdresseCentre() %></td>
                                    </tr>
                                <% } %>
                            <% } else { %>
                                <tr>
                                    <td colspan="7" class="text-center">Aucun don trouvé</td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<% if (admin != null || readOnlyMode) { %>
<div class="row">
    <div class="col-md-6">
        <div class="card <%= readOnlyMode ? "read-only-mode" : "" %>">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-filter"></i> Filtres</h3>
            </div>
            <div class="card-body">
                <form id="filterForm" class="row g-3">
                    <div class="col-md-6">
                        <label for="dateDebut" class="form-label">Date début</label>
                        <input type="date" class="form-control" id="dateDebut">
                    </div>
                    <div class="col-md-6">
                        <label for="dateFin" class="form-label">Date fin</label>
                        <input type="date" class="form-control" id="dateFin">
                    </div>
                    <div class="col-md-6">
                        <label for="groupeSanguin" class="form-label">Groupe sanguin</label>
                        <select class="form-control" id="groupeSanguin">
                            <option value="">Tous</option>
                            <option value="A+">A+</option>
                            <option value="A-">A-</option>
                            <option value="B+">B+</option>
                            <option value="B-">B-</option>
                            <option value="AB+">AB+</option>
                            <option value="AB-">AB-</option>
                            <option value="O+">O+</option>
                            <option value="O-">O-</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label for="centre" class="form-label">Centre</label>
                        <select class="form-control" id="centre">
                            <option value="">Tous</option>
                            <% 
                                CentreDonService cs = new CentreDonService();
                                for (CentreDon c : cs.findAll()) { 
                            %>
                                <option value="<%= c.getNameCentre() %>"><%= c.getNameCentre() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-12 text-center mt-3">
                        <button type="button" id="applyFilter" class="btn btn-primary">
                            <i class="bi bi-funnel"></i> Appliquer les filtres
                        </button>
                        <button type="button" id="resetFilter" class="btn btn-secondary">
                            <i class="bi bi-arrow-counterclockwise"></i> Réinitialiser
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div class="col-md-6">
        <div class="card <%= readOnlyMode ? "read-only-mode" : "" %>">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-graph-up"></i> Statistiques</h3>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <div class="stats-card text-center">
                            <h3>Total des dons</h3>
                            <div class="number"><%= donService.findAll().size() %></div>
                            <div class="description">Tous les dons enregistrés</div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="stats-card text-center">
                            <h3>Centres actifs</h3>
                            <div class="number"><%= cs.findAll().size() %></div>
                            <div class="description">Centres de don participants</div>
                        </div>
                    </div>
                </div>
                <div class="text-center mt-3">
                    <a href="${pageContext.request.contextPath}/RouteController?page=donGraph" class="btn btn-outline-primary">
                        <i class="bi bi-bar-chart-fill"></i> Voir les graphiques détaillés
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<% } %>

<script>
    // Fonction de recherche dans le tableau
    document.addEventListener('DOMContentLoaded', function() {
        const searchInput = document.getElementById('searchHistory');
        const table = document.getElementById('historyTable');
        const rows = table.getElementsByTagName('tr');
        
        searchInput.addEventListener('keyup', function() {
            const searchText = searchInput.value.toLowerCase();
            
            for (let i = 1; i < rows.length; i++) {
                const row = rows[i];
                const cells = row.getElementsByTagName('td');
                let found = false;
                
                for (let j = 0; j < cells.length; j++) {
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
        
        <% if (admin != null || readOnlyMode) { %>
        // Filtrage avancé
        document.getElementById('applyFilter').addEventListener('click', function() {
            const dateDebut = document.getElementById('dateDebut').value;
            const dateFin = document.getElementById('dateFin').value;
            const groupeSanguin = document.getElementById('groupeSanguin').value;
            const centre = document.getElementById('centre').value;
            
            for (let i = 1; i < rows.length; i++) {
                const row = rows[i];
                const cells = row.getElementsByTagName('td');
                let showRow = true;
                
                // Filtre par date
                if (dateDebut && dateFin) {
                    const dateCell = cells[1].textContent;
                    const dateParts = dateCell.split('/');
                    const rowDate = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]);
                    const startDate = new Date(dateDebut);
                    const endDate = new Date(dateFin);
                    
                    if (rowDate < startDate || rowDate > endDate) {
                        showRow = false;
                    }
                }
                
                // Filtre par groupe sanguin
                if (groupeSanguin && showRow) {
                    const groupeCell = cells[4].textContent;
                    if (groupeCell !== groupeSanguin) {
                        showRow = false;
                    }
                }
                
                // Filtre par centre
                if (centre && showRow) {
                    const centreCell = cells[5].textContent;
                    if (centreCell !== centre) {
                        showRow = false;
                    }
                }
                
                row.style.display = showRow ? '' : 'none';
            }
        });
        
        // Réinitialiser les filtres
        document.getElementById('resetFilter').addEventListener('click', function() {
            document.getElementById('dateDebut').value = '';
            document.getElementById('dateFin').value = '';
            document.getElementById('groupeSanguin').value = '';
            document.getElementById('centre').value = '';
            
            for (let i = 1; i < rows.length; i++) {
                rows[i].style.display = '';
            }
        });
        <% } %>
    });
</script>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
