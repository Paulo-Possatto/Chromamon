<#import "layout.ftlh" as layout>
<@layout.page title="Internal Server Error - Chromamon">
    <div class="container">
        <div class="row justify-content-center min-vh-75 align-items-center">
            <div class="col-lg-6 text-center">
                <div class="mb-5">
                    <i class="bi bi-exclamation-triangle-fill text-danger" style="font-size: 6rem;"></i>
                </div>

                <h1 class="display-4 fw-bold text-danger mb-4">500</h1>
                <h2 class="fw-bold mb-4">Internal Server Error</h2>

                <p class="lead text-muted mb-4">
                    We're experiencing some technical difficulties. Our team has been notified and is working to resolve the issue.
                </p>

                <div class="alert alert-info" role="alert">
                    <i class="bi bi-info-circle me-2"></i>
                    <strong>Error ID:</strong> ${timestamp?c}-${(requestId!"unknown")}
                </div>

                <div class="d-flex justify-content-center gap-3 flex-wrap mt-4">
                    <a href="/" class="btn btn-primary">
                        <i class="bi bi-house me-2"></i>Go Home
                    </a>
                    <button onclick="window.history.back()" class="btn btn-outline-primary">
                        <i class="bi bi-arrow-left me-2"></i>Go Back
                    </button>
                    <button onclick="window.location.reload()" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-clockwise me-2"></i>Try Again
                    </button>
                </div>

                <div class="mt-5">
                    <p class="text-muted small">
                        If this problem persists, please contact our support team at
                        <a href="mailto:support@chromamon.com">support@chromamon.com</a>
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