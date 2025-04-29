<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Statistiques des dons par centre</title>
  <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
  <style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      font-family: sans-serif;
      background: #f5f5f5;
    }
    #chart-container {
      width: 800px;
      max-width: 100%;
      background: white;
      padding: 2rem;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
      border-radius: 0.5rem;
    }
    .chart-title {
      text-align: center;
      margin-bottom: 1.5rem;
      color: #333;
      font-size: 1.5rem;
    }
  </style>
</head>
<body>

  <div id="chart-container">
    <h1 class="chart-title">Statistiques des dons par centre</h1>
    <canvas id="donChart"></canvas>
  </div>

  <script>
    document.addEventListener('DOMContentLoaded', () => {
      // 1. Appel au service REST pour les statistiques des dons
      fetch('http://localhost:8080/gestionDesDonsDeSang/DonByCentreController')
        .then(response => {
          if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
          }
          return response.json();
        })
        .then(data => {
          // 2. Extraction des données
          const labels = data.map(item => item.centreName);
          const valeurs = data.map(item => item.donCount);

          // 3. Couleurs dynamiques pour chaque barre
          const backgroundColors = [
            'rgba(255, 99, 132, 0.7)',
            'rgba(54, 162, 235, 0.7)',
            'rgba(255, 206, 86, 0.7)',
            'rgba(75, 192, 192, 0.7)',
            'rgba(153, 102, 255, 0.7)',
            'rgba(255, 159, 64, 0.7)'
          ];

          // 4. Création du graphique
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
        })
        .catch(err => {
          console.error('Erreur de chargement des données:', err);
          const container = document.getElementById('chart-container');
          container.innerHTML = `
            <p style="color:red; text-align:center;">
              Erreur lors du chargement des statistiques des dons.<br>
              ${err.message}
            </p>`;
        });
    });
  </script>

</body>
</html>