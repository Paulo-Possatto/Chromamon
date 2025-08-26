<#macro page title="Chromamon">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${title}</title>
        <link rel="icon" href="/static/images/favicon.ico" type="image/x-icon"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
        <style>
            :root {
                --primary: #005293;
                --secondary: #6c757d;
                --success: #198754;
                --danger: #dc3545;
                --warning: #ffc107;
                --info: #0dcaf0;
                --light: #f8f9fa;
                --dark: #212529;
            }

            .hero-section {
                background: linear-gradient(rgba(0, 82, 147, 0.8), rgba(0, 82, 147, 0.8)), url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" viewBox="0 0 100 100"><rect width="100" height="100" fill="%23005293"/><path d="M0 0L100 100" stroke="%23004a82" stroke-width="1"/><path d="M100 0L0 100" stroke="%23004a82" stroke-width="1"/></svg>');
                background-size: cover;
                color: white;
                padding: 7rem 0;
            }

            .feature-icon {
                font-size: 2.5rem;
                color: var(--primary);
                margin-bottom: 1rem;
            }

            .navbar {
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            .card-hover {
                transition: transform 0.3s ease;
            }

            .card-hover:hover {
                transform: translateY(-5px);
            }

            .footer {
                background-color: var(--light);
                padding: 3rem 0;
                margin-top: 3rem;
            }

            .chart-container {
                position: relative;
                height: 300px;
                margin-bottom: 2rem;
            }

            .login-container {
                max-width: 400px;
                margin: 2rem auto;
                padding: 2rem;
                border: 1px solid #dee2e6;
                border-radius: 0.375rem;
            }

            .analysis-table {
                font-size: 0.9rem;
            }

            .dashboard-card {
                height: 100%;
            }
        </style>
    </head>
    <body>
    <#-- Navbar Component -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white sticky-top">
        <div class="container">
            <a class="navbar-brand" href="/">
                <span class="fw-bold text-primary"><img src="/static/images/chromamon.png" width="32" height="32"> CHROMAMON</span>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/analyses">Analyses</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/dashboards">Dashboards</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/diagnostics">Diagnostics</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/transformers">Transformers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/audit">Audit</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            More
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/about">About</a></li>
                            <li><a class="dropdown-item" href="/contact">Contact</a></li>
                        </ul>
                    </li>
                </ul>
                <div class="d-flex">
                    <a href="/login" class="btn btn-outline-primary me-2">Login</a>
                    <a href="/create-account" class="btn btn-primary">Create Account</a>
                </div>
            </div>
        </div>
    </nav>

    <#-- Main Content Section -->
    <main>
        <#-- O conteúdo específico da página será inserido aqui -->
        <#nested>
    </main>

    <#-- Footer Component -->
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 mb-4 mb-lg-0">
                    <h5 class="fw-bold mb-4"><i class="bi bi-lightning-charge"></i> CHROMAMON</h5>
                    <p class="text-muted">Advanced transformer oil chromatography analysis for predictive maintenance and risk assessment.</p>
                </div>
                <div class="col-lg-2 col-md-4 mb-4 mb-md-0">
                    <h5 class="fw-bold mb-4">Product</h5>
                    <ul class="list-unstyled">
                        <li class="mb-2"><a href="/features" class="text-decoration-none text-muted">Features</a></li>
                        <li class="mb-2"><a href="/analyses" class="text-decoration-none text-muted">Analyses</a></li>
                        <li class="mb-2"><a href="/dashboards" class="text-decoration-none text-muted">Dashboards</a></li>
                        <li class="mb-2"><a href="/diagnostics" class="text-decoration-none text-muted">Diagnostics</a></li>
                    </ul>
                </div>
            </div>
            <hr class="my-4">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <p class="text-muted mb-0">&copy; 2025 Chromamon App - Check out on <a href="https://github.com/Paulo-Possatto/Chromamon">GitHub</a>.</p>
                </div>
                <div class="col-md-6 text-md-end">
                    <a href="/terms" class="text-decoration-none text-muted me-3">Terms of Service</a>
                    <a href="/privacy" class="text-decoration-none text-muted me-3">Privacy Policy</a>
                    <a href="/cookies" class="text-decoration-none text-muted">Cookie Policy</a>
                </div>
            </div>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>