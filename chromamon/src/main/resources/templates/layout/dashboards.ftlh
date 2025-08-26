<#import "layout.ftlh" as layout>
<@layout.page title="Dashboards - Chromamon">
    <div class="container-fluid py-4">
        <h1 class="h3 fw-bold mb-4">Analytics Dashboards</h1>

        <div class="row mb-4">
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-0 shadow-sm dashboard-card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h6 class="card-title text-muted mb-0">Total Analyses</h6>
                                <h3 class="fw-bold mt-2">${dashboardStats.totalAnalyses}</h3>
                            </div>
                            <div class="bg-primary bg-opacity-10 p-3 rounded">
                                <i class="bi bi-clipboard-data text-primary fs-4"></i>
                            </div>
                        </div>
                        <p class="text-success mt-3 mb-0"><i class="bi bi-arrow-up me-1"></i>${dashboardStats.analysisGrowth} since last month</p>
                    </div>
                </div>
            </div>

            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-0 shadow-sm dashboard-card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h6 class="card-title text-muted mb-0">Transformers at Risk</h6>
                                <h3 class="fw-bold mt-2">${dashboardStats.transformersAtRisk}</h3>
                            </div>
                            <div class="bg-danger bg-opacity-10 p-3 rounded">
                                <i class="bi bi-exclamation-triangle text-danger fs-4"></i>
                            </div>
                        </div>
                        <p class="text-danger mt-3 mb-0"><i class="bi bi-arrow-up me-1"></i>${dashboardStats.riskGrowth} since last month</p>
                    </div>
                </div>
            </div>

            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-0 shadow-sm dashboard-card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h6 class="card-title text-muted mb-0">Normal Status</h6>
                                <h3 class="fw-bold mt-2">${dashboardStats.normalPercentage}%</h3>
                            </div>
                            <div class="bg-success bg-opacity-10 p-3 rounded">
                                <i class="bi bi-check-circle text-success fs-4"></i>
                            </div>
                        </div>
                        <p class="text-success mt-3 mb-0"><i class="bi bi-arrow-up me-1"></i>${dashboardStats.normalGrowth} since last month</p>
                    </div>
                </div>
            </div>

            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-0 shadow-sm dashboard-card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h6 class="card-title text-muted mb-0">Avg. Response Time</h6>
                                <h3 class="fw-bold mt-2">${dashboardStats.avgResponseTime}h</h3>
                            </div>
                            <div class="bg-info bg-opacity-10 p-3 rounded">
                                <i class="bi bi-speedometer2 text-info fs-4"></i>
                            </div>
                        </div>
                        <p class="text-danger mt-3 mb-0"><i class="bi bi-arrow-up me-1"></i>${dashboardStats.responseTimeChange} since last month</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-lg-8 mb-4">
                <div class="card border-0 shadow-sm h-100">
                    <div class="card-body">
                        <h5 class="card-title">Gas Concentration Trends</h5>
                        <div class="chart-container">
                            <canvas id="gasTrendChart"></canvas>
                        </div>
                        <div class="d-flex justify-content-center">
                            <div class="me-3"><span class="badge bg-primary me-1">H₂</span></div>
                            <div class="me-3"><span class="badge bg-danger me-1">CH₄</span></div>
                            <div><span class="badge bg-warning me-1">C₂H₂</span></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 mb-4">
                <div class="card border-0 shadow-sm h-100">
                    <div class="card-body">
                        <h5 class="card-title">Transformer Status</h5>
                        <div class="chart-container">
                            <canvas id="transformerStatusChart"></canvas>
                        </div>
                        <div class="d-flex justify-content-center">
                            <div class="me-3"><span class="badge bg-success me-1">Normal</span></div>
                            <div class="me-3"><span class="badge bg-warning me-1">Attention</span></div>
                            <div><span class="badge bg-danger me-1">Critical</span></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-6 mb-4">
                <div class="card border-0 shadow-sm h-100">
                    <div class="card-body">
                        <h5 class="card-title">Diagnostic Method Results</h5>
                        <div class="chart-container">
                            <canvas id="diagnosticMethodChart"></canvas>
                        </div>
                        <p class="text-center text-muted mt-2">Number of analyses by diagnostic method</p>
                    </div>
                </div>
            </div>

            <div class="col-lg-6 mb-4">
                <div class="card border-0 shadow-sm h-100">
                    <div class="card-body">
                        <h5 class="card-title">Fault Type Distribution</h5>
                        <div class="chart-container">
                            <canvas id="faultTypeChart"></canvas>
                        </div>
                        <div class="row text-center mt-3">
                            <div class="col-6 col-md-3 mb-2">
                                <span class="badge bg-primary me-1">PD</span> Partial Discharge
                            </div>
                            <div class="col-6 col-md-3 mb-2">
                                <span class="badge bg-success me-1">D1</span> Low Energy Discharge
                            </div>
                            <div class="col-6 col-md-3 mb-2">
                                <span class="badge bg-danger me-1">D2</span> High Energy Discharge
                            </div>
                            <div class="col-6 col-md-3 mb-2">
                                <span class="badge bg-warning me-1">T1</span> Thermal Fault
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        // Gas Trend Chart
        const gasTrendCtx = document.getElementById('gasTrendChart').getContext('2d');
        const gasTrendChart = new Chart(gasTrendCtx, {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
                datasets: [
                    {
                        label: 'H₂',
                        data: [65, 59, 80, 81, 56, 55, 40],
                        borderColor: '#005293',
                        backgroundColor: 'rgba(0, 82, 147, 0.1)',
                        tension: 0.3,
                        fill: true
                    },
                    {
                        label: 'CH₄',
                        data: [28, 48, 40, 19, 86, 27, 90],
                        borderColor: '#dc3545',
                        backgroundColor: 'rgba(220, 53, 69, 0.1)',
                        tension: 0.3,
                        fill: true
                    },
                    {
                        label: 'C₂H₂',
                        data: [5, 10, 7, 15, 12, 8, 18],
                        borderColor: '#ffc107',
                        backgroundColor: 'rgba(255, 193, 7, 0.1)',
                        tension: 0.3,
                        fill: true
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'top',
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Concentration (ppm)'
                        }
                    }
                }
            }
        });

        // Transformer Status Chart
        const statusCtx = document.getElementById('transformerStatusChart').getContext('2d');
        const statusChart = new Chart(statusCtx, {
            type: 'doughnut',
            data: {
                labels: ['Normal', 'Attention', 'Critical'],
                datasets: [{
                    data: [87, 10, 3],
                    backgroundColor: [
                        '#198754',
                        '#ffc107',
                        '#dc3545'
                    ],
                    hoverOffset: 4
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                    }
                }
            }
        });

        // Diagnostic Method Chart
        const diagnosticCtx = document.getElementById('diagnosticMethodChart').getContext('2d');
        const diagnosticChart = new Chart(diagnosticCtx, {
            type: 'bar',
            data: {
                labels: ['Duval', 'Rogers', 'Key Gas', 'Doernenburg', 'IEC', 'Duval 2', 'Rogers 4', 'CIGRE'],
                datasets: [{
                    label: 'Number of Analyses',
                    data: [65, 59, 80, 81, 56, 55, 40, 30],
                    backgroundColor: '#005293',
                    borderRadius: 5
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

        // Fault Type Chart
        const faultCtx = document.getElementById('faultTypeChart').getContext('2d');
        const faultChart = new Chart(faultCtx, {
            type: 'pie',
            data: {
                labels: ['Partial Discharge', 'Low Energy Discharge', 'High Energy Discharge', 'Thermal Fault'],
                datasets: [{
                    data: [45, 30, 15, 10],
                    backgroundColor: [
                        '#005293',
                        '#198754',
                        '#dc3545',
                        '#ffc107'
                    ],
                    hoverOffset: 4
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                    }
                }
            }
        });
    </script>
</@layout.page>