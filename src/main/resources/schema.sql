DROP TABLE IF EXISTS TOOL_PATCH_CONFIG;
CREATE TABLE TOOL_PATCH_CONFIG (
    id NUMBER PRIMARY KEY,
    open_book_prefix VARCHAR2(255) NOT NULL,
    open_book_fix_string VARCHAR2(255) NOT NULL,
    open_book_major_version VARCHAR2(255) NOT NULL,
    open_book_minor_version VARCHAR2(255) NOT NULL,
    open_book_rpp1 NUMBER NOT NULL,
    open_book_rpp2 NUMBER NOT NULL,
    open_book_rpp3 NUMBER NOT NULL,
    open_book_last_deployed_patch VARCHAR2(255) NOT NULL,
    
    closed_book_prefix VARCHAR2(255) NOT NULL,
    closed_book_fix_string VARCHAR2(255) NOT NULL,
    closed_book_major_version VARCHAR2(255) NOT NULL,
    closed_book_minor_version VARCHAR2(255) NOT NULL,
    closed_book_rpp1 NUMBER NOT NULL,
    closed_book_rpp2 NUMBER NOT NULL,
    closed_book_rpp3 NUMBER NOT NULL,
    closed_book_last_deployed_patch VARCHAR2(255) NOT NULL,
    
    max_mig_prefix VARCHAR2(255) NOT NULL,
    max_mig_fix_string VARCHAR2(255) NOT NULL,
    max_mig_major_version VARCHAR2(255) NOT NULL,
    max_mig_minor_version VARCHAR2(255) NOT NULL,
    max_mig_rpp1 NUMBER NOT NULL,
    max_mig_rpp2 NUMBER NOT NULL,
    max_mig_rpp3 NUMBER NOT NULL,
    max_mig_last_deployed_patch VARCHAR2(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS TOOL_PATCH_REQUEST_SEQ START WITH 1 INCREMENT BY 1;

DROP TABLE IF EXISTS TOOL_APP_TASK;
CREATE TABLE TOOL_APP_TASK (
    id NUMBER PRIMARY KEY,
    task_number VARCHAR(50) NOT NULL,
    task_short_description VARCHAR(255) NOT NULL,
    book_type VARCHAR(50) NOT NULL,
    line_type VARCHAR(50) NOT NULL,
    patch_type VARCHAR(50) NOT NULL,
    patch_number VARCHAR(100),
    requested_by VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS TOOL_APP_USER;
CREATE TABLE TOOL_APP_USER (
    id NUMBER PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
);

