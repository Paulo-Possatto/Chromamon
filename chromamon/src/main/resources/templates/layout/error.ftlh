<#import "layout.ftlh" as layout>
<@layout.page title="${(errorTitle!'Error')} - Chromamon">
    <div class="container">
        <div class="row justify-content-center min-vh-75 align-items-center">
            <div class="col-lg-8">
                <div class="card border-0 shadow-lg">
                    <div class="card-body p-5 text-center">
                        <div class="mb-4">
                            <#if status?? && status == "500">
                                <i class="bi bi-exclamation-triangle-fill text-danger" style="font-size: 4rem;"></i>
                            <#elseif status?? && status == "404">
                                <i class="bi bi-search text-warning" style="font-size: 4rem;"></i>
                            <#elseif status?? && status == "403">
                                <i class="bi bi-shield-x text-danger" style="font-size: 4rem;"></i>
                            <#else>
                                <i class="bi bi-exclamation-circle text-info" style="font-size: 4rem;"></i>
                            </#if>
                        </div>

                        <#if status??>
                            <h1 class="display-6 fw-bold mb-3">${status}</h1>
                        </#if>

                        <h2 class="h3 fw-bold mb-3">
                            ${errorTitle!'Something went wrong'}
                        </h2>

                        <p class="lead text-muted mb-4">
                            ${errorMessage!'We encountered an unexpected error. Please try again later.'}
                        </p>

                        <#if errorDetails??>
                            <div class="alert alert-light border text-start mb-4">
                                <h6 class="fw-bold">Technical Details:</h6>
                                <small class="text-muted">${errorDetails}</small>
                            </div>
                        </#if>

                        <#if timestamp??>
                            <div class="alert alert-info mb-4">
                                <i class="bi bi-info-circle me-2"></i>
                                <small>
                                    <strong>Error ID:</strong> ${timestamp?c}-${(path!"unknown")?hash?c}
                                    <br><strong>Time:</strong> ${timestamp?datetime}
                                </small>
                            </div>
                        </#if>

                        <div class="d-flex justify-content-center gap-3 flex-wrap">
                            <a href="/" class="btn btn-primary">
                                <i class="bi bi-house me-2"></i>Go Home
                            </a>
                            <button onclick="window.history.back()" class="btn btn-outline-primary">
                                <i class="bi bi-arrow-left me-2"></i>Go Back
                            </button>
                            <#if status?? && (status == "500" || status == "503")>
                                <button onclick="window.location.reload()" class="btn btn-outline-secondary">
                                    <i class="bi bi-arrow-clockwise me-2"></i>Try Again
                                </button>
                            </#if>
                        </div>

                        <div class="mt-4 pt-3 border-top">
                            <p class="text-muted small mb-2">
                                Need help? Contact our support team:
                            </p>
                            <p class="mb-0">
                                <a href="mailto:support@chromamon.com" class="text-decoration-none">
                                    <i class="bi bi-envelope me-1"></i>support@chromamon.com
                                </a>
                                <span class="mx-2">|</span>
                                <a href="tel:+15551234567" class="text-decoration-none">
                                    <i class="bi bi-telephone me-1"></i>+1 (555) 123-4567
                                </a>
                            </p>
                        </div>
                    </div>
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