
-- profile table
INSERT INTO user_profile (user_profile_id, profile_name, profile_description) VALUES ('prf-1', 'Admin', 'Admin Profile');

-- module table
INSERT INTO app_module (module_id, module_name, module_description) VALUES ('mod-1', 'Users', 'Users Management');

-- module privileges
INSERT INTO module_privilege (module_privilege_id, module_id, privilege_code, privilege_description)
      VALUES ('priv-1', 'mod-1', 'CREATE_USER', 'Add new User'), ('priv-2', 'mod-1', 'UPDATE_USER', 'Update User');

-- profile privileges
INSERT INTO profile_privilege (user_profile_id, module_privilege_id) VALUES ('prf-1', 'priv-1'), ('prf-1', 'priv-2');

-- user app plain pwd: my-pwd-test
INSERT INTO user_app (user_app_id, username, user_pwd, is_active, user_profile_id)
       VALUES ('user-id', 'testing', '$2a$10$hl9dMriNccMfs1Gptzor8OSKh2hht3XL57ohtLfun35dF6bYqm4MC', true, 'prf-1');

-- oauth_client_details plain secret: my-pwd-test
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
       VALUES ('client-id-1', 'resources', '$2a$10$hl9dMriNccMfs1Gptzor8OSKh2hht3XL57ohtLfun35dF6bYqm4MC', 'read, write, trust', 'password, refresh_token', 'redirect', 'CREATE', 1600, 30, '{}', 'true');
