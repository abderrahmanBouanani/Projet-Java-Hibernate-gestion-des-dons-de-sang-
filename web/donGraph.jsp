<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="title" value="Statistiques des Dons" />
    <jsp:param name="currentPage" value="donGraph" />
    <jsp:param name="pageTitle" value="Statistiques des Dons de Sang" />
</jsp:include>

<style>
    /* Correction du problème d'affichage des graphiques */
    .content {
        margin-left: 250px;
        width: calc(100% - 250px);
        overflow-x: hidden;
    }
    
    @media (max-width: 768px) {
        .content {
            margin-left: 0;
            width: 100%;
        }
    }
    
    .chart-container {
        position: relative;
        height: 300px;
        width: 100%;
    }
</style>

<div class="row mb-4">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-bar-chart-fill"></i> Statistiques des dons par centre</h3>
            </div>
            <div class="card-body">
                <div class="chart-container">
                    <canvas id="donChart"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-pie-chart-fill"></i> Répartition par groupe sanguin</h3>
            </div>
            <div class="card-body">
                <div class="chart-container">
                    <canvas id="bloodGroupChart"></canvas>
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-md-6">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title"><i class="bi bi-calendar3"></i> Évolution mensuelle des dons</h3>
            </div>
            <div class="card-body">
                <div class="chart-container">
                    <canvas id="monthlyChart"></canvas>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        // 1. Graphique des dons par centre
        fetch('${pageContext.request.contextPath}/DonByCentreController')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                const labels = data.map(item => item.centreName);
                const valeurs = data.map(item => item.donCount);

                const backgroundColors = [
                    'rgba(255, 99, 132, 0.7)',
                    'rgba(54, 162, 235, 0.7)',
                    'rgba(255, 206, 86, 0.7)',
                    'rgba(75, 192, 192, 0.7)',
                    'rgba(153, 102, 255, 0.7)',
                    'rgba(255, 159, 64, 0.7)'
                ];

                const ctx = document.getElementById('donChart').getContext('2d');
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Nombre de dons',
                            data: valeurs,
                            backgroundColor: backgroundColors,
                            borderColor: backgroundColors.map(color => color.replace('0.7', '1')),
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: true,
                        plugins: {
                            legend: {
                                display: false
                            },
                            tooltip: {
                                callbacks: {
                                    label: function(context) {
                                        return `${context.parsed.y} don(s)`;
                                    }
                                }
                            }
                        },
                        scales: {
                            y: {
                                beginAtZero: true,
                                ticks: { 
                                    stepSize: 1,
                                    precision: 0
                                },
                                title: {
                                    display: true,
                                    text: 'Nombre de dons'
                                }
                            },
                            x: {
                                title: {
                                    display: true,
                                    text: 'Centres de don'
                                }
                            }
                        },
                        animation: {
                            duration: 1500,
                            easing: 'easeInOutQuad'
                        }
                    }
                });
                
                // 2. Charger les données pour le graphique des groupes sanguins
                fetch('${pageContext.request.contextPath}/BloodGroupStatsController')
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(bloodData => {
                        const bloodGroups = bloodData.map(item => item.bloodGroup);
                        const bloodGroupCounts = bloodData.map(item => item.donCount);
                        
                        const bloodGroupCtx = document.getElementById('bloodGroupChart').getContext('2d');
                        new Chart(bloodGroupCtx, {
                            type: 'pie',
                            data: {
                                labels: bloodGroups,
                                datasets: [{
                                    data: bloodGroupCounts,
                                    backgroundColor: [
                                        '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', 
                                        '#9966FF', '#FF9F40', '#32CD32', '#8B0000'
                                    ],
                                    borderWidth: 1
                                }]
                            },
                            options: {
                                responsive: true,
                                maintainAspectRatio: true,
                                plugins: {
                                    legend: {
                                        position: 'right'
                                    },
                                    tooltip: {
                                        callbacks: {
                                            label: function(context) {
                                                const label = context.label || '';
                                                const value = context.raw || 0;
                                                const total = context.dataset.data.reduce((acc, val) => acc + val, 0);
                                                const percentage = Math.round((value / total) * 100);
                                                return `${label}: ${value} (${percentage}%)`;
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    })
                    .catch(err => {
                        console.error('Erreur de chargement des données de groupes sanguins:', err);
                        document.getElementById('bloodGroupChart').innerHTML = `
                            <div class="alert alert-danger">
                                Erreur lors du chargement des statistiques par groupe sanguin.<br>
                                ${err.message}
                            </div>`;
                    });
                
                // 3. Charger les données pour l'évolution mensuelle
                fetch('${pageContext.request.contextPath}/MonthlyDonStatsController')
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(monthlyData => {
                        const months = monthlyData.map(item => item.monthName);
                        const monthlyCounts = monthlyData.map(item => item.donCount);
                        
                        const monthlyCtx = document.getElementById('monthlyChart').getContext('2d');
                        new Chart(monthlyCtx, {
                            type: 'line',
                            data: {
                                labels: months,
                                datasets: [{
                                    label: 'Dons par mois',
                                    data: monthlyCounts,
                                    fill: false,
                                    borderColor: 'rgb(75, 192, 192)',
                                    tension: 0.1,
                                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                    pointBackgroundColor: 'rgb(75, 192, 192)',
                                    pointBorderColor: '#fff',
                                    pointHoverBackgroundColor: '#fff',
                                    pointHoverBorderColor: 'rgb(75, 192, 192)'
                                }]
                            },
                            options: {
                                responsive: true,
                                maintainAspectRatio: true,
                                scales: {
                                    y: {
                                        beginAtZero: true,
                                        title: {
                                            display: true,
                                            text: 'Nombre de dons'
                                        }
                                    },
                                    x: {
                                        title: {
                                            display: true,
                                            text: 'Mois'
                                        }
                                    }
                                }
                            }
                        });
                    })
                    .catch(err => {
                        console.error('Erreur de chargement des données mensuelles:', err);
                        document.getElementById('monthlyChart').innerHTML = `
                            <div class="alert alert-danger">
                                Erreur lors du chargement des statistiques mensuelles.<br>
                                ${err.message}
                            </div>`;
                    });
            })
            .catch(err => {
                console.error('Erreur de chargement des données par centre:', err);
                document.getElementById('donChart').innerHTML = `
                    <div class="alert alert-danger">
                        Erreur lors du chargement des statistiques des dons par centre.<br>
                        ${err.message}
                    </div>`;
            });
    });
</script>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
