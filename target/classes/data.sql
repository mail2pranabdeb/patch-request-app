-- Default Admin User (password is 'admin' encoded with BCrypt)
INSERT INTO TOOL_APP_USER (id, username, password, roles) VALUES (TOOL_PATCH_REQUEST_SEQ.nextval, 'admin', '{noop}admin', 'ROLE_ADMIN');

-- Insert initial config
INSERT INTO TOOL_PATCH_CONFIG (id, date_format, sequence_length, open_book_last_deployed_patch, closed_book_last_deployed_patch) VALUES (TOOL_PATCH_REQUEST_SEQ.nextval, 'yyyyMMdd', 3, '', '');
