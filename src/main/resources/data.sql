-- Insert initial config
MERGE INTO TOOL_PATCH_CONFIG KEY(id) VALUES (1, 
    'Rpp', 'App', '11.3', '04', 71, 72, 73, 'Rpp_App.11.3.04.71.001_20260328', 
    'Mig', 'App', '11.3', '04', 16, 17, 18, 'Mig_App.11.3.04.16.001_20260328', 
    'Max', 'App', '11.3', '04', 11, 12, 13, 'Max_App.11.3.04.11.001_20260328'
);

-- Insert admin user
MERGE INTO TOOL_APP_USER KEY(username) VALUES (1, 'pranab', '{noop}pranab', 'ROLE_ADMIN');
