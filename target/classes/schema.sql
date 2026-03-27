CREATE SEQUENCE TOOL_PATCH_REQUEST_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE TOOL_APP_USER (
    id NUMBER PRIMARY KEY,
    username VARCHAR2(255) NOT NULL UNIQUE,
    password VARCHAR2(255) NOT NULL,
    roles VARCHAR2(255) NOT NULL
);

CREATE TABLE TOOL_PATCH_CONFIG (
    id NUMBER PRIMARY KEY,
    date_format VARCHAR2(50),
    sequence_length NUMBER,
    open_book_last_deployed_patch VARCHAR2(255),
    closed_book_last_deployed_patch VARCHAR2(255)
);

CREATE TABLE TOOL_APP_TASK (
    id NUMBER PRIMARY KEY,
    patch_type VARCHAR2(100),
    book_type VARCHAR2(100),
    line_type VARCHAR2(100),
    task_number VARCHAR2(255),
    task_short_description VARCHAR2(500),
    requested_by VARCHAR2(255),
    patch_number VARCHAR2(255)
);
