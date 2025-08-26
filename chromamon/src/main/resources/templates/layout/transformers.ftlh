<#import "layout.ftlh" as layout>
<@layout.page title="Transformers - Chromamon">
    <div class="container-fluid py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="h3 fw-bold">Transformer Management</h1>
            <a href="/add-transformer" class="btn btn-primary">
                <i class="bi bi-plus-circle me-2"></i>Add Transformer
            </a>
        </div>

        <div class="card border-0 shadow-sm mb-4">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="transformerIdFilter" class="form-label">Transformer ID</label>
                        <input type="text" class="form-control" id="transformerIdFilter" placeholder="Search by ID...">
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="transformerStatusFilter" class="form-label">Status</label>
                        <select class="form-select" id="transformerStatusFilter">
                            <option selected>All Statuses</option>
                            <option>Normal</option>
                            <option>Attention</option>
                            <option>Critical</option>
                            <option>Decommissioned</option>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="transformerLocationFilter" class="form-label">Location</label>
                        <select class="form-select" id="transformerLocationFilter">
                            <option selected>All Locations</option>
                            <#list locations as location>
                                <option>${location}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="transformerSearch" class="form-label">Search</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="transformerSearch" placeholder="Search transformers...">
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
                            <th scope="col">Transformer ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Location</th>
                            <th scope="col">Manufacturer</th>
                            <th scope="col">Installation Date</th>
                            <th scope="col">Last Analysis</th>
                            <th scope="col">Status</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list transformers as transformer>
                            <tr>
                                <td>${transformer.id}</td>
                                <td>${transformer.name}</td>
                                <td>${transformer.location}</td>
                                <td>${transformer.manufacturer}</td>
                                <td>${transformer.installationDate?string('MMM dd, yyyy')}</td>
                                <td>
                                    <#if transformer.lastAnalysisDate??>
                                        ${transformer.lastAnalysisDate?string('MMM dd, yyyy')}
                                    <#else>
                                        Never
                                    </#if>
                                </td>
                                <td>
                                    <#if transformer.status == "Normal">
                                        <span class="badge bg-success">Normal</span>
                                    <#elseif transformer.status == "Attention">
                                        <span class="badge bg-warning">Attention</span>
                                    <#elseif transformer.status == "Critical">
                                        <span class="badge bg-danger">Critical</span>
                                    <#else>
                                        <span class="badge bg-secondary">Decommissioned</span>
                                    </#if>
                                </td>
                                <td>
                                    <a href="/edit-transformer/${transformer.id}" class="btn btn-sm btn-outline-primary me-1">
                                        <i class="bi bi-pencil"></i>
                                    </a>
                                    <a href="/analyses?transformer=${transformer.id}" class="btn btn-sm btn-outline-info me-1">
                                        <i class="bi bi-clipboard-data"></i>
                                    </a>
                                    <button class="btn btn-sm btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteTransformerModal" data-transformer-id="${transformer.id}" data-transformer-name="${transformer.name}">
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
                        <span class="text-muted">Showing ${transformers?size} of ${totalTransformers} results</span>
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
    <div class="modal fade" id="deleteTransformerModal" tabindex="-1" aria-labelledby="deleteTransformerModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteTransformerModalLabel">Confirm Deletion</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete transformer <span id="transformerName" class="fw-bold"></span>?</p>
                    <p class="text-danger">This action will also delete all associated analyses and cannot be undone.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <form id="deleteTransformerForm" method="POST" action="">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger">Delete Transformer</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        // JavaScript para manipular o modal de exclusão de transformador
        var deleteTransformerModal = document.getElementById('deleteTransformerModal');
        deleteTransformerModal.addEventListener('show.bs.modal', function (event) {
            var button = event.relatedTarget;
            var transformerId = button.getAttribute('data-transformer-id');
            var transformerName = button.getAttribute('data-transformer-name');

            var modalBody = deleteTransformerModal.querySelector('.modal-body');
            var transformerNameSpan = document.getElementById('transformerName');
            transformerNameSpan.textContent = transformerName;

            var form = document.getElementById('deleteTransformerForm');
            form.action = '/delete-transformer/' + transformerId;
        });
    </script>
</@layout.page>