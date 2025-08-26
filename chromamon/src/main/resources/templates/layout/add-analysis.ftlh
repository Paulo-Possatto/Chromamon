<#import "layout.ftlh" as layout>
<@layout.page title="Add Analysis - Chromamon">
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3 fw-bold">Add New Analysis</h1>
            <a href="/analyses" class="btn btn-outline-secondary">
                <i class="bi bi-arrow-left me-2"></i>Back to Analyses
            </a>
        </div>

        <div class="card border-0 shadow-sm">
            <div class="card-body">
                <ul class="nav nav-tabs" id="analysisTabs" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="manual-tab" data-bs-toggle="tab" data-bs-target="#manual" type="button" role="tab" aria-controls="manual" aria-selected="true">Manual Entry</button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="file-tab" data-bs-toggle="tab" data-bs-target="#file" type="button" role="tab" aria-controls="file" aria-selected="false">File Upload</button>
                    </li>
                </ul>

                <div class="tab-content p-4" id="analysisTabsContent">
                    <div class="tab-pane fade show active" id="manual" role="tabpanel" aria-labelledby="manual-tab">
                        <form action="/add-analysis" method="POST">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="inputType" value="manual"/>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="transformer" class="form-label">Transformer</label>
                                    <select class="form-select" id="transformer" name="transformerId" required>
                                        <option selected disabled value="">Select a transformer</option>
                                        <#list transformers as transformer>
                                            <option value="${transformer.id}">${transformer.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="analysisDate" class="form-label">Analysis Date</label>
                                    <input type="date" class="form-control" id="analysisDate" name="analysisDate" required>
                                </div>
                            </div>

                            <h5 class="mt-4 mb-3">Gas Concentrations (ppm)</h5>
                            <div class="row">
                                <div class="col-md-4 mb-3">
                                    <label for="h2" class="form-label">Hydrogen (H₂)</label>
                                    <input type="number" class="form-control" id="h2" name="h2" min="0" step="0.01">
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label for="ch4" class="form-label">Methane (CH₄)</label>
                                    <input type="number" class="form-control" id="ch4" name="ch4" min="0" step="0.01">
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label for="c2h2" class="form-label">Acetylene (C₂H₂)</label>
                                    <input type="number" class="form-control" id="c2h2" name="c2h2" min="0" step="0.01">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 mb-3">
                                    <label for="c2h4" class="form-label">Ethylene (C₂H₄)</label>
                                    <input type="number" class="form-control" id="c2h4" name="c2h4" min="0" step="0.01">
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label for="c2h6" class="form-label">Ethane (C₂H₆)</label>
                                    <input type="number" class="form-control" id="c2h6" name="c2h6" min="0" step="0.01">
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label for="co" class="form-label">Carbon Monoxide (CO)</label>
                                    <input type="number" class="form-control" id="co" name="co" min="0" step="0.01">
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 mb-3">
                                    <label for="co2" class="form-label">Carbon Dioxide (CO₂)</label>
                                    <input type="number" class="form-control" id="co2" name="co2" min="0" step="0.01">
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label for="o2" class="form-label">Oxygen (O₂)</label>
                                    <input type="number" class="form-control" id="o2" name="o2" min="0" step="0.01">
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label for="n2" class="form-label">Nitrogen (N₂)</label>
                                    <input type="number" class="form-control" id="n2" name="n2" min="0" step="0.01">
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="notes" class="form-label">Notes</label>
                                <textarea class="form-control" id="notes" name="notes" rows="3"></textarea>
                            </div>

                            <div class="d-flex justify-content-end mt-4">
                                <button type="reset" class="btn btn-outline-secondary me-3">Reset</button>
                                <button type="submit" class="btn btn-primary">Save Analysis</button>
                            </div>
                        </form>
                    </div>

                    <div class="tab-pane fade" id="file" role="tabpanel" aria-labelledby="file-tab">
                        <form action="/upload-analysis" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <div class="border rounded p-4 text-center mb-4">
                                <div class="mb-3">
                                    <i class="bi bi-cloud-upload display-4 text-primary"></i>
                                </div>
                                <h5>Upload Chromatography Data File</h5>
                                <p class="text-muted">Supported formats: .xls, .xlsx, .csv</p>

                                <div class="mb-3">
                                    <input class="form-control" type="file" id="dataFile" name="dataFile" accept=".xls,.xlsx,.csv" required>
                                </div>

                                <div class="form-text">Maximum file size: 10MB</div>
                            </div>

                            <div class="d-flex justify-content-end">
                                <button type="submit" class="btn btn-primary">Process File</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.page>