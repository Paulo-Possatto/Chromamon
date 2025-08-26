<#import "layout.ftlh" as layout>
<@layout.page title="Forgot Password - Chromamon">
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card border-0 shadow">
                    <div class="card-body p-5">
                        <div class="text-center mb-5">
                            <h1 class="h3 fw-bold">Reset Your Password</h1>
                            <p class="text-muted">Enter your email address and we'll send you a link to reset your password</p>
                        </div>

                        <#if message??>
                            <div class="alert alert-info" role="alert">
                                ${message}
                            </div>
                        </#if>

                        <#if error??>
                            <div class="alert alert-danger" role="alert">
                                ${error}
                            </div>
                        </#if>

                        <form action="/forgot-password" method="POST">

                            <div class="mb-4">
                                <label for="email" class="form-label">Email Address</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary btn-lg">Send Reset Link</button>
                            </div>

                            <div class="text-center mt-4">
                                <p class="text-muted">Remember your password? <a href="/login">Back to login</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.page>