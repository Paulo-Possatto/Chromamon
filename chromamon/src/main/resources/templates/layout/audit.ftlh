<#import "layout.ftlh" as layout>
<@layout.page title="Auditing - Chromamon">
    <div class="container-fluid py-4">
        <h1 class="h3 fw-bold mb-4">Audit Log</h1>

        <div class="card border-0 shadow-sm mb-4">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="auditUserFilter" class="form-label">User</label>
                        <select class="form-select" id="auditUserFilter">
                            <option selected>All Users</option>
                            <#list users as user>
                                <option value="${user.id}">${user.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="auditActionFilter" class="form-label">Action</label>
                        <select class="form-select" id="auditActionFilter">
                            <option selected>All Actions</option>
                            <option>CREATE</option>
                            <option>UPDATE</option>
                            <option>DELETE</option>
                            <option>LOGIN</option>
                            <option>LOGOUT</option>
                            <option>DOWNLOAD</option>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="auditDateFilter" class="form-label">Date Range</label>
                        <select class="form-select" id="auditDateFilter">
                            <option selected>Last 7 days</option>
                            <option>Last 24 hours</option>
                            <option>Last 30 days</option>
                            <option>Last 90 days</option>
                            <option>Custom range</option>
                        </select>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="auditSearch" class="form-label">Search</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="auditSearch" placeholder="Search audit log...">
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
                            <th scope="col">Timestamp</th>
                            <th scope="col">User</th>
                            <th scope="col">Action</th>
                            <th scope="col">Resource</th>
                            <th scope="col">Resource ID</th>
                            <th scope="col">Details</th>
                            <th scope="col">IP Address</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list auditLogs as log>
                            <tr>
                                <td>${log.timestamp?string('yyyy-MM-dd HH:mm:ss')}</td>
                                <td>${log.userName}</td>
                                <td>
                                    <#if log.action == "CREATE">
                                        <span class="badge bg-success">CREATE</span>
                                    <#elseif log.action == "UPDATE">
                                        <span class="badge bg-primary">UPDATE</span>
                                    <#elseif log.action == "DELETE">
                                        <span class="badge bg-danger">DELETE</span>
                                    <#elseif log.action == "LOGIN">
                                        <span class="badge bg-info">LOGIN</span>
                                    <#elseif log.action == "LOGOUT">
                                        <span class="badge bg-secondary">LOGOUT</span>
                                    <#else>
                                        <span class="badge bg-warning">${log.action}</span>
                                    </#if>
                                </td>
                                <td>${log.resourceType}</td>
                                <td>${log.resourceId}</td>
                                <td>${log.details!''}</td>
                                <td>${log.ipAddress}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <div class="d-flex justify-content-between align-items-center mt-3">
                    <div>
                        <span class="text-muted">Showing ${auditLogs?size} of ${totalLogs} results</span>
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
</@layout.page>