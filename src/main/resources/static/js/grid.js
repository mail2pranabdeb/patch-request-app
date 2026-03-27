document.addEventListener('DOMContentLoaded', () => {
    const table = document.querySelector('.table');
    if (!table) return;

    const tbody = table.querySelector('tbody');
    const rows = Array.from(tbody.querySelectorAll('tr'));
    
    // Check if the table is empty (contains the "No records" row)
    const isEmpty = rows.length === 1 && rows[0].children.length === 1;
    if (isEmpty) return;

    // Config
    const rowsPerPage = 10;
    let currentPage = 1;
    let filteredRows = [...rows];

    // UI Elements
    const searchInput = document.getElementById('grid-search');
    const exportBtn = document.getElementById('grid-export');
    const paginationContainer = document.getElementById('grid-pagination');
    const pageInfo = document.getElementById('grid-page-info');

    function renderTable() {
        // Clear table
        rows.forEach(r => r.style.display = 'none');
        
        // Calculate bounds
        const totalPages = Math.ceil(filteredRows.length / rowsPerPage) || 1;
        if (currentPage > totalPages) currentPage = totalPages;
        if (currentPage < 1) currentPage = 1;
        
        const start = (currentPage - 1) * rowsPerPage;
        const end = start + rowsPerPage;
        
        // Show visible rows
        filteredRows.slice(start, end).forEach(r => r.style.display = '');

        // Update pagination UI
        if (pageInfo) {
            const startDisplay = filteredRows.length === 0 ? 0 : start + 1;
            const endDisplay = Math.min(end, filteredRows.length);
            pageInfo.textContent = `Showing ${startDisplay} to ${endDisplay} of ${filteredRows.length} entries`;
        }
        
        renderPaginationControls(totalPages);
    }

    function renderPaginationControls(totalPages) {
        if (!paginationContainer) return;
        paginationContainer.innerHTML = '';

        if (totalPages <= 1) return; // Hide pagination if only 1 page

        const prevBtn = document.createElement('button');
        prevBtn.className = 'btn btn-outline';
        prevBtn.style.padding = '4px 10px';
        prevBtn.textContent = 'Previous';
        prevBtn.disabled = currentPage === 1;
        if (currentPage === 1) prevBtn.style.opacity = '0.5';
        prevBtn.onclick = () => { currentPage--; renderTable(); };
        paginationContainer.appendChild(prevBtn);

        // Optional: Page numbers could go here (Left out for simplicity)

        const nextBtn = document.createElement('button');
        nextBtn.className = 'btn btn-outline';
        nextBtn.style.padding = '4px 10px';
        nextBtn.textContent = 'Next';
        nextBtn.disabled = currentPage === totalPages;
        if (currentPage === totalPages) nextBtn.style.opacity = '0.5';
        nextBtn.onclick = () => { currentPage++; renderTable(); };
        paginationContainer.appendChild(nextBtn);
    }

    if (searchInput) {
        searchInput.addEventListener('input', (e) => {
            const query = e.target.value.toLowerCase();
            filteredRows = rows.filter(row => {
                const text = row.textContent.toLowerCase();
                return text.includes(query);
            });
            currentPage = 1;
            renderTable();
        });
    }

    if (exportBtn) {
        exportBtn.addEventListener('click', () => {
            let csv = [];
            const headers = Array.from(table.querySelectorAll('th')).map(th => th.textContent.trim());
            // Remove 'Actions' column which is usually the last one
            headers.pop();
            csv.push(headers.join(','));

            filteredRows.forEach(row => {
                const cells = Array.from(row.querySelectorAll('td'));
                cells.pop(); // Remove actions
                const rowData = cells.map(td => `"${td.textContent.trim().replace(/"/g, '""')}"`);
                csv.push(rowData.join(','));
            });

            const csvFile = new Blob([csv.join('\n')], {type: 'text/csv'});
            const downloadLink = document.createElement('a');
            downloadLink.download = 'patch_requests.csv';
            downloadLink.href = window.URL.createObjectURL(csvFile);
            downloadLink.style.display = 'none';
            document.body.appendChild(downloadLink);
            downloadLink.click();
            downloadLink.remove();
        });
    }

    // Initial Render
    renderTable();
});
