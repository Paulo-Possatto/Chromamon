<#import "layout.ftlh" as layout>
<@layout.page title="Update Analysis - Chromamon">
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3 fw-bold">Update Analysis</h1>
            <a href="/analyses" class="btn btn-outline-secondary">
                <i class="bi bi-arrow-left me-2"></i>Back to Analyses
            </a>
        </div>

        <#if analysis??>
            <div class="card border-0 shadow-sm">
                <div class="card-body">
                    <form action="/update-analysis/${analysis.id}" method="POST">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="transformer" class="form-label">Transformer</label>
                                <select class="form-select" id="transformer" name="transformerId" required>
                                    <#list transformers as transformer>
                                        <option value="${transformer.id}" <#if analysis.transformerId == transformer.id>selected</#if>>${transformer.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="analysisDate" class="form-label">Analysis Date</label>
                                <input type="date" class="form-control" id="analysisDate" name="analysisDate" value="${analysis.analysisDate?string('yyyy-MM-dd')}" required>
                            </div>
                        </div>

                        <h5 class="mt-4 mb-3">Gas Concentrations (ppm)</h5>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="h2" class="form-label">Hydrogen (H₂)</label>
                                <input type="number" class="form-control" id="h2" name="h2" value="${analysis.h2}" min="0" step="0.01">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="ch4" class="form-label">Methane (CH₄)</label>
                                <input type="number" class="form-control" id="ch4" name="ch4" value="${analysis.ch4}" min="0" step="0.01">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="c2h2" class="form-label">Acetylene (C₂H₂)</label>
                                <input type="number" class="form-control" id="c2h2" name="c2h2" value="${analysis.c2h2}" min="0" step="0.01">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="c2h4" class="form-label">Ethylene (C₂H₄)</label>
                                <input type="number" class="form-control" id="c2h4" name="c2h4" value="${analysis.c2h4}" min="0" step="0.01">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="c2h6" class="form-label">Ethane (C₂H₆)</label>
                                <input type="number" class="form-control" id="c2h6" name="c2h6" value="${analysis.c2h6}" min="0" step="0.01">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="co" class="form-label">Carbon Monoxide (CO)</label>
                                <input type="number" class="form-control" id="co" name="co" value="${analysis.co}" min="0" step="0.01">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="co2" class="form-label">Carbon Dioxide (CO₂)</label>
                                <input type="number" class="form-control" id="co2" name="co2" value="${analysis.co2}" min="0" step="0.01">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="o2" class="form-label">Oxygen (O₂)</label>
                                <input type="number" class="form-control" id="o2" name="o2" value="${analysis.o2}" min="0" step="0.01">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="n2" class="form-label">Nitrogen (N₂)</label>
                                <input type="number" class="form-control" id="n2" name="n2" value="${analysis.n2}" min="0" step="0.01">
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="notes" class="form-label">Notes</label>
                            <textarea class="form-control" id="notes" name="notes" rows="3">${analysis.notes!''}</textarea>
                        </div>

                        <div class="d-flex justify-content-end mt-4">
                            <button type="button" class="btn btn-outline-danger me-auto" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete Analysis</button>
                            <button type="reset" class="btn btn-outline-secondary me-3">Reset</button>
                            <button type="submit" class="btn btn-primary">Update Analysis</button>
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
                            Are you sure you want to delete this analysis? This action cannot be undone.
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <form action="/delete-analysis/${analysis.id}" method="POST">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="alert alert-danger" role="alert">
                Analysis not found.
            </div>
        </#if>
    </div>
</@layout.page>