<#import "layout.ftlh" as layout>
<@layout.page title="Login - Chromamon">

    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card border-0 shadow">
                    <div class="card-body p-5">
                        <div class="text-center mb-5">
                            <h1 class="h3 fw-bold">Sign In to Chromamon</h1>
                            <p class="text-muted">Enter your credentials to access your account</p>
                        </div>

                        <#if error??>
                            <div class="alert alert-danger" role="alert">
                                ${error}
                            </div>
                        </#if>

                        <form action="/login" method="POST">

                            <div class="mb-3">
                                <label for="username" class="form-label">Email Address</label>
                                <input type="email" class="form-control" id="username" name="username" required>
                            </div>

                            <div class="mb-4">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                                <div class="form-text">
                                    <a href="/forgot-password">Forgot your password?</a>
                                </div>
                            </div>

                            <div class="form-check mb-4">
                                <input class="form-check-input" type="checkbox" id="remember-me" name="remember-me">
                                <label class="form-check-label" for="remember-me">
                                    Remember me
                                </label>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary btn-lg">Sign In</button>
                            </div>

                            <div class="text-center mt-4">
                                <p class="text-muted">Don't have an account? <a href="/create-account">Create one</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.page>