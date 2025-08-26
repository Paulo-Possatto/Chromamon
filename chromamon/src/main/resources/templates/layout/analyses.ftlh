<#import "layout.ftlh" as layout>
<@layout.page title="Analyses - Chromamon">
    <div class="container-fluid py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3 fw-bold">Transformer Oil Analyses</h1>
            <a href="/add-analysis" class="btn btn-primary">
                <i class="bi bi-plus-circle me-2"></i>Add Analysis
            </a>
        </div>

        <div class="card border-0 shadow-sm mb-4">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="transformerFilter" class="form-label">Transformer</label>
                        <select class="form-select" id="transformerFilter">
                            <option selected>All Transformers</option>
                            <option>Transformer A-101</option>
                            <option>Transformer B-205</option>
                            <option>Transformer C-308</option>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="dateFilter" class="form-label">Date Range</label>
                        <select class="form-select" id="dateFilter">
                            <option selected>Last 30 days</option>
                            <option>Last 7 days</option>
                            <option>Last 90 days</option>
                            <option>Custom range</option>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="statusFilter" class="form-label">Status</label>
                        <select class="form-select" id="statusFilter">
                            <option selected>All Statuses</option>
                            <option>Normal</option>
                            <option>Attention</option>
                            <option>Critical</option>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="searchFilter" class="form-label">Search</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="searchFilter" placeholder="Search analyses...">
                            <button class="btn btn-outline-secondary" type="button">
                                <i class="bi bi-search"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="card border-0 shadow-sm">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover analysis-table">
                        <thead>
                        <tr>
                            <th scope="col">
                                <input class="form-check-input" type="checkbox" id="selectAll">
                            </th>
                            <th scope="col">Analysis ID</th>
                            <th scope="col">Transformer</th>
                            <th scope="col">Date</th>
                            <th scope="col">H₂ (ppm)</th>
                            <th scope="col">CH₄ (ppm)</th>
                            <th scope="col">C₂H₂ (ppm)</th>
                            <th scope="col">C₂H₄ (ppm)</th>
                            <th scope="col">C₂H₆ (ppm)</th>
                            <th scope="col">Status</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list analyses as analysis>
                            <tr>
                                <td>
                                    <input class="form-check-input" type="checkbox">
                                </td>
                                <td>${analysis.id}</td>
                                <td>${analysis.transformer}</td>
                                <td>${analysis.date?string('MMM dd, yyyy')}</td>
                                <td>${analysis.h2}</td>
                                <td>${analysis.ch4}</td>
                                <td>${analysis.c2h2}</td>
                                <td>${analysis.c2h4}</td>
                                <td>${analysis.c2h6}</td>
                                <td>
                                    <#if analysis.status == "Normal">
                                        <span class="badge bg-success">Normal</span>
                                    <#elseif analysis.status == "Attention">
                                        <span class="badge bg-warning">Attention</span>
                                    <#else>
                                        <span class="badge bg-danger">Critical</span>
                                    </#if>
                                </td>
                                <td>
                                    <a href="/update-analysis/${analysis.id}" class="btn btn-sm btn-outline-primary me-1">
                                        <i class="bi bi-pencil"></i>
                                    </a>
                                    <button class="btn btn-sm btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" data-analysis-id="${analysis.id}">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <div class="d-flex justify-content-between align-items-center mt-3">
                    <div>
                        <span class="text-muted">Showing ${analyses?size} of ${totalAnalyses} results</span>
                    </div>
                    <nav>
                        <ul class="pagination mb-0">
                            <#if currentPage gt 1>
                                <li class="page-item">
                                    <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                                </li>
                            </#if>

                            <#list 1..totalPages as page>
                                <li class="page-item <#if page == currentPage>active</#if>">
                                    <a class="page-link" href="?page=${page}">${page}</a>
                                </li>
                            </#list>

                            <#if currentPage < totalPages>
                                <li class="page-item">
                                    <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                                </li>
                            </#if>
                        </ul>
                    </nav>
                </div>
            </div>
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
                    <form id="deleteForm" method="POST" action="">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        // JavaScript para manipular o modal de exclusão
        var deleteModal = document.getElementById('deleteModal');
        deleteModal.addEventListener('show.bs.modal', function (event) {
            var button = event.relatedTarget;
            var analysisId = button.getAttribute('data-analysis-id');
            var form = document.getElementById('deleteForm');
            form.action = '/delete-analysis/' + analysisId;
        });
    </script>
</@layout.page>