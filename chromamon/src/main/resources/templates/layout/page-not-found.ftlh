<#import "layout.ftlh" as layout>
<@layout.page title="Page Not Found - Chromamon">
    <div class="container">
        <div class="row justify-content-center min-vh-75 align-items-center">
            <div class="col-lg-6 text-center">
                <div class="mb-5">
                    <i class="bi bi-search text-warning" style="font-size: 6rem;"></i>
                </div>

                <h1 class="display-4 fw-bold text-warning mb-4">404</h1>
                <h2 class="fw-bold mb-4">Page Not Found</h2>

                <p class="lead text-muted mb-4">
                    The page you're looking for doesn't exist or has been moved.
                </p>

                <div class="d-flex justify-content-center gap-3 flex-wrap mt-4">
                    <a href="/" class="btn btn-primary">
                        <i class="bi bi-house me-2"></i>Go Home
                    </a>
                    <a href="/analyses" class="btn btn-outline-primary">
                        <i class="bi bi-graph-up me-2"></i>View Analyses
                    </a>
                    <a href="/dashboard" class="btn btn-outline-primary">
                        <i class="bi bi-speedometer2 me-2"></i>Dashboard
                    </a>
                </div>

                <div class="mt-5">
                    <p class="text-muted">
                        Looking for something specific? Try searching or check our main sections above.
                    </p>
                </div>
            </div>
        </div>
    </div>

    <style>
        .min-vh-75 {
            min-height: 75vh;
        }
    </style>
</@layout.page>