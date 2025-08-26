<#import "layout.ftlh" as layout>
<@layout.page title="Contact - Chromamon">
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-10 mx-auto">
                <h1 class="display-5 fw-bold text-center mb-5">Contact Us</h1>

                <div class="row">
                    <div class="col-md-5">
                        <div class="card border-0 shadow-sm h-100">
                            <div class="card-body p-4">
                                <h4 class="card-title mb-4">Get in Touch</h4>

                                <div class="d-flex mb-4">
                                    <div class="me-3">
                                        <i class="bi bi-geo-alt-fill text-primary fs-4"></i>
                                    </div>
                                    <div>
                                        <h5>Address</h5>
                                        <p class="mb-0">123 Tech Park Drive<br>Austin, TX 78701<br>United States</p>
                                    </div>
                                </div>

                                <div class="d-flex mb-4">
                                    <div class="me-3">
                                        <i class="bi bi-telephone-fill text-primary fs-4"></i>
                                    </div>
                                    <div>
                                        <h5>Phone</h5>
                                        <p class="mb-0">+1 (555) 123-4567</p>
                                    </div>
                                </div>

                                <div class="d-flex mb-4">
                                    <div class="me-3">
                                        <i class="bi bi-envelope-fill text-primary fs-4"></i>
                                    </div>
                                    <div>
                                        <h5>Email</h5>
                                        <p class="mb-0">info@chromamon.com</p>
                                    </div>
                                </div>

                                <div class="d-flex">
                                    <div class="me-3">
                                        <i class="bi bi-clock-fill text-primary fs-4"></i>
                                    </div>
                                    <div>
                                        <h5>Business Hours</h5>
                                        <p class="mb-0">Monday-Friday: 8am-6pm CST<br>Saturday: 10am-2pm CST<br>Sunday: Closed</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-7">
                        <div class="card border-0 shadow-sm">
                            <div class="card-body p-4">
                                <h4 class="card-title mb-4">Send us a Message</h4>

                                <form action="/contact" method="POST">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="name" class="form-label">Full Name</label>
                                            <input type="text" class="form-control" id="name" name="name" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="email" class="form-label">Email Address</label>
                                            <input type="email" class="form-control" id="email" name="email" required>
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="company" class="form-label">Company</label>
                                        <input type="text" class="form-control" id="company" name="company">
                                    </div>

                                    <div class="mb-3">
                                        <label for="subject" class="form-label">Subject</label>
                                        <select class="form-select" id="subject" name="subject">
                                            <option selected>General Inquiry</option>
                                            <option>Technical Support</option>
                                            <option>Sales Question</option>
                                            <option>Partnership Opportunity</option>
                                            <option>Demo Request</option>
                                        </select>
                                    </div>

                                    <div class="mb-3">
                                        <label for="message" class="form-label">Message</label>
                                        <textarea class="form-control" id="message" name="message" rows="5" required></textarea>
                                    </div>

                                    <div class="form-check mb-4">
                                        <input class="form-check-input" type="checkbox" id="newsletter" name="newsletter">
                                        <label class="form-check-label" for="newsletter">
                                            Subscribe to our newsletter for updates
                                        </label>
                                    </div>

                                    <button type="submit" class="btn btn-primary">Send Message</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="card border-0 shadow-sm mt-5">
                    <div class="card-body p-4">
                        <h4 class="card-title mb-4 text-center">Find Us</h4>
                        <div class="text-center">
                            <img src="data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='800' height='300' viewBox='0 0 800 300'><rect width='800' height='300' fill='%23f8f9fa'/><text x='400' y='150' font-family='Arial' font-size='20' text-anchor='middle' fill='%236c757d'>Map Location - Austin, TX</text></svg>" class="img-fluid rounded" alt="Map Location">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.page>