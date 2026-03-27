document.addEventListener('DOMContentLoaded', () => {
    const columnDefs = [
        { field: "id", headerName: "ID", maxWidth: 90 },
        { field: "bookType", headerName: "Book Type" },
        { field: "lineType", headerName: "Line Type", maxWidth: 120 },
        { field: "taskNumber", headerName: "Task Number" },
        { field: "requestedBy", headerName: "Requested By" },
        {
            field: "patchNumber", headerName: "Patch Number", cellRenderer: params => {
                return params.value ? `<span class="badge badge-info">${params.value}</span>` : '';
            }
        },
        {
            field: "actions", headerName: "Actions", sortable: false, filter: false, maxWidth: 120, cellRenderer: params => {
                if (isAuthenticated) {
                    return `
                    <a href="/patch-request-app/tasks/edit/${params.data.id}" class="btn btn-outline" style="padding:2px 6px;" title="Edit"><svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M17 3a2.828 2.828 0 1 1 4 4L7.5 20.5 2 22l1.5-5.5L17 3z"/></svg></a>
                    <a href="/patch-request-app/tasks/delete/${params.data.id}" class="btn btn-danger" style="padding:2px 6px;" onclick="return confirm('Delete this request?');" title="Delete"><svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 6h18M19 6l-1 14a2 2 0 0 1-2 2H8a2 2 0 0 1-2-2L5 6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/></svg></a>
                `;
                } else {
                    return `<span class="badge" style="color:var(--text-muted);" title="Login to edit"><svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="11" width="18" height="11" rx="2" ry="2"/><path d="M7 11V7a5 5 0 0 1 10 0v4"/></svg></span>`;
                }
            }
        }
    ];

    const gridOptions = {
        columnDefs: columnDefs,
        rowData: rowData,
        pagination: true,
        paginationPageSize: 10,
        defaultColDef: {
            sortable: true,
            filter: true,
            resizable: true,
            flex: 1,
            minWidth: 110
        }
    };

    const gridDiv = document.querySelector('#myGrid');

    let gridApi;
    if (typeof agGrid.createGrid === 'function') {
        gridApi = agGrid.createGrid(gridDiv, gridOptions);
    } else {
        new agGrid.Grid(gridDiv, gridOptions);
        gridApi = gridOptions.api;
    }

    // Connect Search Input
    const searchInput = document.getElementById('grid-search');
    if (searchInput) {
        searchInput.addEventListener('input', () => {
            if (gridApi && typeof gridApi.setGridOption === 'function') {
                gridApi.setGridOption('quickFilterText', searchInput.value);
            } else if (gridOptions.api && typeof gridOptions.api.setQuickFilter === 'function') {
                gridOptions.api.setQuickFilter(searchInput.value);
            }
        });
    }

    // Connect CSV Export
    const exportBtn = document.getElementById('grid-export');
    if (exportBtn) {
        exportBtn.addEventListener('click', () => {
            if (gridApi) {
                gridApi.exportDataAsCsv({ fileName: 'patch_requests.csv', columnKeys: ['id', 'bookType', 'lineType', 'taskNumber', 'requestedBy', 'patchNumber'] });
            } else if (gridOptions.api) {
                gridOptions.api.exportDataAsCsv({ fileName: 'patch_requests.csv', columnKeys: ['id', 'bookType', 'lineType', 'taskNumber', 'requestedBy', 'patchNumber'] });
            }
        });
    }
});
