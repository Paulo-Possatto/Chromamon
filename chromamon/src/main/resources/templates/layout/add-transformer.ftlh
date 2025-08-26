<#import "layout.ftlh" as layout>
<@layout.page title="Add Transformer - Chromamon">
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3 fw-bold">Add New Transformer</h1>
            <a href="/transformers" class="btn btn-outline-secondary">
                <i class="bi bi-arrow-left me-2"></i>Back to Transformers
            </a>
        </div>

        <div class="card border-0 shadow-sm">
            <div class="card-body">
                <form action="/add-transformer" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="transformerId" class="form-label">Transformer ID</label>
                            <input type="text" class="form-control" id="transformerId" name="transformerId" required>
                            <div class="form-text">Unique identifier for the transformer</div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="name" class="form-label">Transformer Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="location" class="form-label">Location</label>
                            <select class="form-select" id="location" name="location" required>
                                <option selected disabled value="">Select a location</option>
                                <#list locations as location>
                                    <option value="${location}">${location}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="substation" class="form-label">Substation</label>
                            <input type="text" class="form-control" id="substation" name="substation">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="manufacturer" class="form-label">Manufacturer</label>
                            <select class="form-select" id="manufacturer" name="manufacturer" required>
                                <option selected disabled value="">Select manufacturer</option>
                                <option>General Electric</option>
                                <option>Siemens</option>
                                <option>ABB</option>
                                <option>Westinghouse</option>
                                <option>Other</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="model" class="form-label">Model</label>
                            <input type="text" class="form-control" id="model" name="model">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <label for="installationDate" class="form-label">Installation Date</label>
                            <input type="date" class="form-control" id="installationDate" name="installationDate">
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="capacity" class="form-label">Capacity (MVA)</label>
                            <input type="number" class="form-control" id="capacity" name="capacity" step="0.1" min="0">
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="voltage" class="form-label">Voltage (kV)</label>
                            <input type="number" class="form-control" id="voltage" name="voltage" step="0.1" min="0">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="coolingType" class="form-label">Cooling Type</label>
                            <select class="form-select" id="coolingType" name="coolingType">
                                <option selected disabled value="">Select cooling type</option>
                                <option>ONAN</option>
                                <option>ONAF</option>
                                <option>OFAF</option>
                                <option>OFWF</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="oilType" class="form-label">Oil Type</label>
                            <select class="form-select" id="oilType" name="oilType">
                                <option selected disabled value="">Select oil type</option>
                                <option>Mineral Oil</option>
                                <option>Silicone Oil</option>
                                <option>Bio-based Oil</option>
                                <option>Other</option>
                            </select>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="notes" class="form-label">Notes</label>
                        <textarea class="form-control" id="notes" name="notes" rows="3"></textarea>
                    </div>

                    <div class="d-flex justify-content-end mt-4">
                        <button type="reset" class="btn btn-outline-secondary me-3">Reset</button>
                        <button type="submit" class="btn btn-primary">Add Transformer</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@layout.page>