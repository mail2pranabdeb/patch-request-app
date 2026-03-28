DROP TABLE IF EXISTS TOOL_PATCH_CONFIG;
CREATE TABLE TOOL_PATCH_CONFIG (
    id NUMBER PRIMARY KEY,
    open_book_rpp1 NUMBER NOT NULL,
    open_book_rpp2 NUMBER NOT NULL,
    open_book_rpp3 NUMBER NOT NULL,
    open_book_series VARCHAR2(255) NOT NULL,
    open_book_fixed_string VARCHAR2(255) NOT NULL,
    open_book_last_deployed_patch VARCHAR2(255) NOT NULL,
    
    closed_book_rpp1 NUMBER NOT NULL,
    closed_book_rpp2 NUMBER NOT NULL,
    closed_book_rpp3 NUMBER NOT NULL,
    closed_book_series VARCHAR2(255) NOT NULL,
    closed_book_fixed_string VARCHAR2(255) NOT NULL,
    closed_book_last_deployed_patch VARCHAR2(255) NOT NULL,
    
    max_mig_rpp1 NUMBER NOT NULL,
    max_mig_rpp2 NUMBER NOT NULL,
    max_mig_rpp3 NUMBER NOT NULL,
    max_mig_series VARCHAR2(255) NOT NULL,
    max_mig_fixed_string VARCHAR2(255) NOT NULL,
    max_mig_last_deployed_patch VARCHAR2(255) NOT NULL
);

DROP TABLE IF EXISTS TOOL_APP_TASK;
CREATE TABLE TOOL_APP_TASK (
    id IDENTITY PRIMARY KEY,
    ticket_id VARCHAR(50) NOT NULL,
    ticket_summary VARCHAR(255) NOT NULL,
    book_type VARCHAR(50) NOT NULL,
    line_type VARCHAR(50) NOT NULL,
    patch_type VARCHAR(50) NOT NULL,
    patch_number VARCHAR(100),
    requested_by VARCHAR(100),
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'Pending'
);
