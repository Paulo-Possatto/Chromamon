<#import "layout.ftlh" as layout>
<@layout.page title="Diagnostics - Chromamon">
<div class="container-fluid py-4">
    <h1 class="h3 fw-bold mb-4">Diagnostic Results</h1>

    <div class="card border-0 shadow-sm mb-4">
        <div class="card-body">
            <div class="row">
                <div class="col-md-3 mb-3">
                    <label for="diagTransformerFilter" class="form-label">Transformer</label>
                    <select class="form-select" id="diagTransformerFilter" onchange="filterDiagnostics()">
                        <option value="" selected>All Transformers</option>
                        <#list transformers as transformer>
                            <option value="${transformer.id}">${transformer.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="col-md-3 mb-3">
                    <label for="diagDateFilter" class="form-label">Date Range</label>
                    <select class="form-select" id="diagDateFilter" onchange="filterDiagnostics()">
                        <option value="30" selected>Last 30 days</option>
                        <option value="7">Last 7 days</option>
                        <option value="90">Last 90 days</option>
                        <option value="0">Custom range</option>
                    </select>
                </div>
                <div class="col-md-3 mb-3">
                    <label for="diagMethodFilter" class="form-label">Diagnostic Method</label>
                    <select class="form-select" id="diagMethodFilter" onchange="filterDiagnostics()">
                        <option value="" selected>All Methods</option>
                        <#option value="duval">Duval Triangle</option>
                        <option value="rogers">Rogers Ratio</option>
                        <option value="keygas">Key Gas</option>
                        <option value="doernenburg">Doernenburg Ratio</option>
                        <option value="iec">IEC 60599</option>
                    </select>
                </div>
                <div class="col-md-3 mb-3">
                    <label for="diagFaultFilter" class="form-label">Fault Type</label>
                    <select class="form-select" id="diagFaultFilter" onchange="filterDiagnostics()">
                        <option value="" selected>All Types</option>
                        <option value="none">No Fault</option>
                        <option value="pd">Partial Discharge (PD)</option>
                        <option value="d1">Low Energy Discharge (D1)</option>
                        <option value="d2">High Energy Discharge (D2)</option>
                        <option value="t1">Thermal Fault (T1)</option>
                        <option value="t2">Thermal Fault (T2)</option>
                        <option value="t3">Thermal Fault (T3)</option>
                    </select>
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
                        <th scope="col">Analysis ID</th>
                        <th scope="col">Transformer</th>
                        <th scope="col">Date</th>
                        <th scope="col">Duval Triangle</th>
                        <th scope="col">Rogers Ratio</th>
                        <th scope="col">Key Gas</th>
                        <th scope="col">Doernenburg</th>
                        <th scope="col">IEC 60599</th>
                        <th scope="col">Consensus</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list diagnostics as diagnostic>
                        <tr class="diagnostic-row"
                            data-transformer="${diagnostic.transformerId}"
                            data-method="all"
                            data-fault="${diagnostic.consensusFault?lower_case?replace(' ', '-')}">
                            <td>${diagnostic.analysisId}</td>
                            <td>${diagnostic.transformerName}</td>
                            <td>${diagnostic.analysisDate?string('MMM dd, yyyy')}</td>
                            <td>
                                <#if diagnostic.duvalFault == "No Fault">
                                    <span class="badge bg-success">No Fault</span>
                                <#else>
                                    <span class="badge bg-${diagnostic.duvalSeverity}">${diagnostic.duvalFault}</span>
                                </#if>
                            </td>
                            <td>
                                <#if diagnostic.rogersFault == "No Fault">
                                    <span class="badge bg-success">No Fault</span>
                                <#else>
                                    <span class="badge bg-${diagnostic.rogersSeverity}">${diagnostic.rogersFault}</span>
                                </#if>
                            </td>
                            <td>
                                <#if diagnostic.keyGasFault == "No Fault">
                                    <span class="badge bg-success">No Fault</span>
                                <#else>
                                    <span class="badge bg-${diagnostic.keyGasSeverity}">${diagnostic.keyGasFault}</span>
                                </#if>
                            </td>
                            <td>
                                <#if diagnostic.doernenburgFault == "No Fault">
                                    <span class="badge bg-success">No Fault</span>
                                <#else>
                                    <span class="badge bg-${diagnostic.doernenburgSeverity}">${diagnostic.doernenburgFault}</span>
                                </#if>
                            </td>
                            <td>
                                <#if diagnostic.iecFault == "No Fault">
                                    <span class="badge bg-success">No Fault</span>
                                <#else>
                                    <span class="badge bg-${diagnostic.iecSeverity}">${diagnostic.iecFault}</span>
                                </#if>
                            </td>
                            <td>
                                <#if diagnostic.consensusFault == "No Fault">
                                    <span class="badge bg-success">No Fault</span>
                                <#else>
                                    <span class="badge bg-${diagnostic.consensusSeverity}">${diagnostic.consensusFault}</span>
                                </#if>
                            </td>
                            <td>
                                <a href="/diagnostic-report/${diagnostic.analysisId}" class="btn btn-sm btn-outline-primary">
                                    <i class="bi bi-file-text"></i> Report
                                </a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

            <div class="d-flex justify-content-between align-items-center mt-3">
                <div>
                    <span class="text-muted">Showing ${diagnostics?size} of ${totalDiagnostics} results</span>
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

<script>
    function filterDiagnostics() {
        const transformerFilter = document.getElementById('diagTransformerFilter').value;
        const methodFilter = document.getElementById('diagMethodFilter').value;
        const faultFilter = document.getElementById('diagFaultFilter').value;

        const rows = document.querySelectorAll('.diagnostic-row');

        rows.forEach(row => {
            let show = true;

            // Filter by transformer
            if (transformerFilter && row.getAttribute('data-transformer') !== transformerFilter) {
                show = false;
            }

            // Filter by fault type
            if (faultFilter) {
                if (faultFilter === 'none' && row.getAttribute('data-fault') !== 'no-fault') {
                    show = false;
                } else if (faultFilter !== 'none' && row.getAttribute('data-fault') !== faultFilter) {
                    show = false;
                }
            }

            row.style.display = show ? '' : 'none';
        });
    }
</script>
</@layout.page>