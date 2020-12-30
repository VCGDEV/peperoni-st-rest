-- should use schema
CREATE TABLE user_profile (
   user_profile_id varchar(36) not null primary key,
   profile_name varchar(150) not null,
   profile_description varchar(512)
);

CREATE TABLE user_app (
   user_app_id varchar(36) not null primary key,
   username varchar(256) not null,
   user_pwd varchar(512) not null,
   is_active boolean,
   user_profile_id varchar(36) not null,
   customer_id varchar(36),
   employee_id varchar(36),
   CONSTRAINT fk_user_profile FOREIGN KEY(user_profile_id) REFERENCES user_profile(user_profile_id)
);

CREATE TABLE app_module (
   module_id varchar(36) not null primary key,
   module_name varchar(100) not null,
   module_description varchar(250) not null
);

CREATE TABLE module_privilege (
   module_privilege_id varchar(36) not null primary key,
   module_id varchar(36) not null,
   privilege_code varchar(50) not null,
   privilege_description varchar(100),
   CONSTRAINT fk_module_privilege FOREIGN KEY(module_id) REFERENCES app_module(module_id) 
);

CREATE TABLE profile_privilege (
   user_profile_id varchar(36) not null,
   module_privilege_id varchar(36) not null,
   primary key(user_profile_id, module_privilege_id)
);

CREATE TABLE customer(
   customer_id varchar(36) not null primary key,
   customer_name varchar(250) not null,
   email varchar(300), 
   is_active boolean
);

CREATE TABLE phone_data (
   phone_data_id varchar(36) not null primary key,
   customer_id varchar(36) not null,
   phone varchar(10) not null,
   phone_type varchar(20),
   is_active boolean,
   CONSTRAINT fk_customer_phone_data FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE address (
   address_id varchar(36) not null primary key,
   customer_id varchar(36) not null,
   zip_code varchar(6),
   city varchar(100),
   suburb varchar(100),
   ext_number varchar(100),
   int_number varchar(100),
   street varchar(200) not null,
   street1 varchar(200),
   street2 varchar(200),
   extra_references varchar(500),
   principal_ind boolean,
   CONSTRAINT fk_customer_address FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE employee (
   employee_id varchar(36) not null primary key,
   employee_name varchar(250) not null
);


create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

create table oauth_code (
  code VARCHAR(256),
  authentication bytea
);

create table oauth_approvals (
	user_id VARCHAR(256),
	client_id VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expires_at TIMESTAMP,
	lastModified_at TIMESTAMP
);
