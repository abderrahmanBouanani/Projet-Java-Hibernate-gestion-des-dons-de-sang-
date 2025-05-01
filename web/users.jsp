<%@page import="entities.Admin"%>
<%@page import="entities.Donneur"%>
<%@page import="services.DonneurService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="title" value="Gestion des Utilisateurs" />
    <jsp:param name="currentPage" value="users" />
    <jsp:param name="pageTitle" value="Gestion des Donneurs" />
</jsp:include>

<%
    Admin admin = (Admin) session.getAttribute("admin");
    if(admin == null){
        response.sendRedirect(request.getContextPath() + "/RouteController?page=login");
        return;
    }
    
    DonneurService ds = new DonneurService();
%>

<div class="row mb-4">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <div class="d-flex justify-content-between align-items-center">
                    <h3 class="card-title"><i class="bi bi-people-fill"></i> Liste des donneurs</h3>
                    <div class="input-group" style="width: 300px;">
                        <input type="text" id="searchDonneur" class="form-control" placeholder="Rechercher un donneur...">
                        <span class="input-group-text"><i class="bi bi-search"></i></span>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped table-hover" id="donneurTable">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Email</th>
                                <th>Groupe Sanguin</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Donneur d : ds.findAll()) { %>
                                <tr>
                                    <td><%= d.getIdUser() %></td>
                                    <td><%= d.getName() %></td>
                                    <td><%= d.getEmail() %></td>
                                    <td><span class="badge bg-danger"><%= d.getGroupeSanguin() %></span></td>
                                    <td>
                                        <a href="ModificationController?op=update&id=<%= d.getIdUser() %>" class="btn btn-sm btn-primary">
                                            <i class="bi bi-pencil-square"></i> Modifier
                                        </a>
                                        <a href="InscriptionController?op=delete&id=<%= d.getIdUser() %>" class="btn btn-sm btn-danger" 
                                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce donneur?')">
                                            <i class="bi bi-trash"></i> Supprimer
                                        </a>
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

<div class="row">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-bar-chart-line"></i> Statistiques des donneurs</h3>
            </div>
            <div class="card-body">
                <div class="stats-container">
                    <div class="stats-item">
                        <div class="stats-value"><%= ds.findAll().size() %></div>
                        <div class="stats-label">Donneurs inscrits</div>
                    </div>
                    <div class="stats-item">
                        <div class="stats-value">
                            <%= ds.findByBloodGroup("A+").size() + ds.findByBloodGroup("A-").size() %>
                        </div>
                        <div class="stats-label">Groupe A</div>
                    </div>
                    <div class="stats-item">
                        <div class="stats-value">
                            <%= ds.findByBloodGroup("B+").size() + ds.findByBloodGroup("B-").size() %>
                        </div>
                        <div class="stats-label">Groupe B</div>
                    </div>
                    <div class="stats-item">
                        <div class="stats-value">
                            <%= ds.findByBloodGroup("AB+").size() + ds.findByBloodGroup("AB-").size() %>
                        </div>
                        <div class="stats-label">Groupe AB</div>
                    </div>
                    <div class="stats-item">
                        <div class="stats-value">
                            <%= ds.findByBloodGroup("O+").size() + ds.findByBloodGroup("O-").size() %>
                        </div>
                        <div class="stats-label">Groupe O</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-plus-circle"></i> Ajouter un donneur</h3>
            </div>
            <div class="card-body">
                <form action="InscriptionController" method="post">
                    <div class="mb-3">
                        <label for="nom" class="form-label">Nom</label>
                        <input type="text" class="form-control" id="nom" name="nom" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label for="mdp" class="form-label">Mot de passe</label>
                        <input type="password" class="form-control" id="mdp" name="mdp" required>
                    </div>
                    <div class="mb-3">
                        <label for="groupeSanguin" class="form-label">Groupe sanguin</label>
                        <select class="form-control" id="groupeSanguin" name="groupeSanguin" required>
                            <option value="" disabled selected>Sélectionnez un groupe sanguin</option>
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
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-person-plus"></i> Ajouter
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const searchInput = document.getElementById('searchDonneur');
        const table = document.getElementById('donneurTable');
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
    });
</script>

<jsp:include page="/WEB-INF/includes/footer.jsp" />

```typescriptreact file="src/java/filter/SecurityFilter.java" isDeleted="true"
...deleted...
