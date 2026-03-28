CREATE SEQUENCE IF NOT EXISTS TOOL_PATCH_REQUEST_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS TOOL_APP_USER (
    id NUMBER PRIMARY KEY,
    username VARCHAR2(255) NOT NULL UNIQUE,
    password VARCHAR2(255) NOT NULL,
    roles VARCHAR2(255) NOT NULL
);

DROP TABLE IF EXISTS TOOL_PATCH_CONFIG;
CREATE TABLE TOOL_PATCH_CONFIG (
    id NUMBER PRIMARY KEY,
    open_book_rpp1 NUMBER NOT NULL,
    open_book_rpp2 NUMBER NOT NULL,
    open_book_rpp3 NUMBER NOT NULL,
    open_book_codefix_patch_format VARCHAR2(255) NOT NULL,
    open_book_datafix_patch_format VARCHAR2(255) NOT NULL,
    open_book_last_deployed_patch VARCHAR2(255) NOT NULL,
    
    closed_book_rpp1 NUMBER NOT NULL,
    closed_book_rpp2 NUMBER NOT NULL,
    closed_book_rpp3 NUMBER NOT NULL,
    closed_book_codefix_patch_format VARCHAR2(255) NOT NULL,
    closed_book_datafix_patch_format VARCHAR2(255) NOT NULL,
    closed_book_last_deployed_patch VARCHAR2(255) NOT NULL,
    
    max_mig_rpp1 NUMBER NOT NULL,
    max_mig_rpp2 NUMBER NOT NULL,
    max_mig_rpp3 NUMBER NOT NULL,
    max_mig_codefix_patch_format VARCHAR2(255) NOT NULL,
    max_mig_datafix_patch_format VARCHAR2(255) NOT NULL,
    max_mig_last_deployed_patch VARCHAR2(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS TOOL_APP_TASK (
    id NUMBER PRIMARY KEY,
    patch_type VARCHAR2(100),
    book_type VARCHAR2(100),
    line_type VARCHAR2(100),
    task_number VARCHAR2(255),
    task_short_description VARCHAR2(500),
    requested_by VARCHAR2(255),
    patch_number VARCHAR2(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
