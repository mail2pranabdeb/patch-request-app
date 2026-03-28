document.addEventListener('DOMContentLoaded', () => {
    const columnDefs = [
        {
            field: "patchNumber", 
            headerName: "Patch Number", 
            minWidth: 350,
            flex: 2,
            cellRenderer: params => {
                return params.value ? `<span class="badge badge-info" style="font-size: 0.95rem; padding: 4px 10px; font-weight: 700;">${params.value}</span>` : '';
            }
        },
        { field: "patchType", headerName: "Patch Type", maxWidth: 120 },
        { field: "bookType", headerName: "Book Type", maxWidth: 130 },
        { field: "taskNumber", headerName: "Task Number" },
        { field: "requestedBy", headerName: "Requested By" },
        { field: "taskShortDescription", headerName: "Description", hide: true },
        {
            field: "actions", headerName: "Actions", sortable: false, filter: false, maxWidth: 120, cellRenderer: params => {
                // Get base path and ensure no double slashes, but keep leading slash
                let base = contextPath || '/';
                if (!base.endsWith('/')) base += '/';
                const rowId = params.data.id || params.data.ID;
                
                // Construct clean URLs
                const editUrl = (base + "tasks/edit/" + rowId).replace(/\/+/g, '/').replace(':/', '://');
                const deleteUrl = (base + "tasks/delete/" + rowId).replace(/\/+/g, '/').replace(':/', '://');

                console.log('Action Click:', { rowId, editUrl, deleteUrl });

                if (isAuthenticated) {
                    return `
                    <a href="${editUrl}" class="btn btn-outline" style="padding:2px 6px;" title="Edit"><svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M17 3a2.828 2.828 0 1 1 4 4L7.5 20.5 2 22l1.5-5.5L17 3z"/></svg></a>
                    <a href="${deleteUrl}" class="btn btn-danger" style="padding:2px 6px;" onclick="return confirm('Delete this request?');" title="Delete"><svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 6h18M19 6l-1 14a2 2 0 0 1-2 2H8a2 2 0 0 1-2-2L5 6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"/></svg></a>
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
        paginationPageSizeSelector: [10, 20, 50, 100],
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
                gridApi.exportDataAsCsv({
                    fileName: 'patch_list.csv',
                    columnKeys: ['patchNumber', 'patchType', 'bookType', 'taskNumber', 'requestedBy', 'taskShortDescription'],
                    suppressQuotes: true
                });
            } else if (gridOptions.api) {
                gridOptions.api.exportDataAsCsv({
                    fileName: 'patch_list.csv',
                    columnKeys: ['patchNumber', 'patchType', 'bookType', 'taskNumber', 'requestedBy', 'taskShortDescription'],
                    suppressQuotes: true
                });
            }
        });
    }
});
