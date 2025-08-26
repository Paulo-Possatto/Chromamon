<#import "layout.ftlh" as layout>
<@layout.page title="Edit Transformer - Chromamon">
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3 fw-bold">Edit Transformer</h1>
            <a href="/transformers" class="btn btn-outline-secondary">
                <i class="bi bi-arrow-left me-2"></i>Back to Transformers
            </a>
        </div>

        <#if transformer??>
            <div class="card border-0 shadow-sm">
                <div class="card-body">
                    <form action="/edit-transformer/${transformer.id}" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="transformerId" class="form-label">Transformer ID</label>
                                <input type="text" class="form-control" id="transformerId" name="transformerId" value="${transformer.id}" readonly>
                                <div class="form-text">Transformer ID cannot be changed</div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="name" class="form-label">Transformer Name</label>
                                <input type="text" class="form-control" id="name" name="name" value="${transformer.name}" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="location" class="form-label">Location</label>
                                <select class="form-select" id="location" name="location" required>
                                    <#list locations as location>
                                        <option value="${location}" <#if transformer.location == location>selected</#if>>${location}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="substation" class="form-label">Substation</label>
                                <input type="text" class="form-control" id="substation" name="substation" value="${transformer.substation!''}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="manufacturer" class="form-label">Manufacturer</label>
                                <select class="form-select" id="manufacturer" name="manufacturer" required>
                                    <option value="General Electric" <#if transformer.manufacturer == "General Electric">selected</#if>>General Electric</option>
                                    <option value="Siemens" <#if transformer.manufacturer == "Siemens">selected</#if>>Siemens</option>
                                    <option value="ABB" <#if transformer.manufacturer == "ABB">selected</#if>>ABB</option>
                                    <option value="Westinghouse" <#if transformer.manufacturer == "Westinghouse">selected</#if>>Westinghouse</option>
                                    <option value="Other" <#if transformer.manufacturer == "Other">selected</#if>>Other</option>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="model" class="form-label">Model</label>
                                <input type="text" class="form-control" id="model" name="model" value="${transformer.model!''}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="installationDate" class="form-label">Installation Date</label>
                                <input type="date" class="form-control" id="installationDate" name="installationDate" value="${transformer.installationDate?string('yyyy-MM-dd')}">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="capacity" class="form-label">Capacity (MVA)</label>
                                <input type="number" class="form-control" id="capacity" name="capacity" value="${transformer.capacity!''}" step="0.1" min="0">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="voltage" class="form-label">Voltage (kV)</label>
                                <input type="number" class="form-control" id="voltage" name="voltage" value="${transformer.voltage!''}" step="0.1" min="0">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="coolingType" class="form-label">Cooling Type</label>
                                <select class="form-select" id="coolingType" name="coolingType">
                                    <option value="">Select cooling type</option>
                                    <option value="ONAN" <#if transformer.coolingType == "ONAN">selected</#if>>ONAN</option>
                                    <option value="ONAF" <#if transformer.coolingType == "ONAF">selected</#if>>ONAF</option>
                                    <option value="OFAF" <#if transformer.coolingType == "OFAF">selected</#if>>OFAF</option>
                                    <option value="OFWF" <#if transformer.coolingType == "OFWF">selected</#if>>OFWF</option>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="oilType" class="form-label">Oil Type</label>
                                <select class="form-select" id="oilType" name="oilType">
                                    <option value="">Select oil type</option>
                                    <option value="Mineral Oil" <#if transformer.oilType == "Mineral Oil">selected</#if>>Mineral Oil</option>
                                    <option value="Silicone Oil" <#if transformer.oilType == "Silicone Oil">selected</#if>>Silicone Oil</option>
                                    <option value="Bio-based Oil" <#if transformer.oilType == "Bio-based Oil">selected</#if>>Bio-based Oil</option>
                                    <option value="Other" <#if transformer.oilType == "Other">selected</#if>>Other</option>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="notes" class="form-label">Notes</label>
                            <textarea class="form-control" id="notes" name="notes" rows="3">${transformer.notes!''}</textarea>
                        </div>

                        <div class="d-flex justify-content-end mt-4">
                            <button type="button" class="btn btn-outline-danger me-auto" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Transformer</button>
                            <button type="reset" class="btn btn-outline-secondary me-3">Reset</button>
                            <button type="submit" class="btn btn-primary">Update Transformer</button>
                        </div>
                    </form>
                </div>
            </div>

        <#-- Delete Confirmation Modal -->
            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Confirm Deletion</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete transformer <span class="fw-bold">${transformer.name}</span>?</p>
                            <p class="text-danger">This action will also delete all associated analyses and cannot be undone.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <form action="/delete-transformer/${transformer.id}" method="POST">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-danger">Delete Transformer</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="alert alert-danger" role="alert">
                Transformer not found.
            </div>
        </#if>
    </div>
</@layout.page>