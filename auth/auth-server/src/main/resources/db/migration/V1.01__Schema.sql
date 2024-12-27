CREATE TABLE oauth2_registered_client
(
    id                            varchar(100)                            NOT NULL,
    client_id                     varchar(100)                            NOT NULL,
    client_id_issued_at           timestamp     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 varchar(200)  DEFAULT NULL,
    client_secret_expires_at      timestamp     DEFAULT NULL,
    client_name                   varchar(200)                            NOT NULL,
    client_authentication_methods varchar(1000)                           NOT NULL,
    authorization_grant_types     varchar(1000)                           NOT NULL,
    redirect_uris                 varchar(1000) DEFAULT NULL,
    post_logout_redirect_uris     varchar(1000) DEFAULT NULL,
    scopes                        varchar(1000)                           NOT NULL,
    client_settings               varchar(2000)                           NOT NULL,
    token_settings                varchar(2000)                           NOT NULL,
    PRIMARY KEY (id)
);


/*
IMPORTANT:
    If using PostgreSQL, update ALL columns defined with 'blob' to 'text',
    as PostgreSQL does not support the 'blob' data type.
*/
CREATE TABLE oauth2_authorization
(
    id                            varchar(100) NOT NULL,
    registered_client_id          varchar(100) NOT NULL,
    principal_name                varchar(200) NOT NULL,
    authorization_grant_type      varchar(100) NOT NULL,
    authorized_scopes             varchar(1000) DEFAULT NULL,
    attributes                    text          DEFAULT NULL,
    state                         varchar(500)  DEFAULT NULL,
    authorization_code_value      text          DEFAULT NULL,
    authorization_code_issued_at  timestamp     DEFAULT NULL,
    authorization_code_expires_at timestamp     DEFAULT NULL,
    authorization_code_metadata   text          DEFAULT NULL,
    access_token_value            text          DEFAULT NULL,
    access_token_issued_at        timestamp     DEFAULT NULL,
    access_token_expires_at       timestamp     DEFAULT NULL,
    access_token_metadata         text          DEFAULT NULL,
    access_token_type             varchar(100)  DEFAULT NULL,
    access_token_scopes           varchar(1000) DEFAULT NULL,
    oidc_id_token_value           text          DEFAULT NULL,
    oidc_id_token_issued_at       timestamp     DEFAULT NULL,
    oidc_id_token_expires_at      timestamp     DEFAULT NULL,
    oidc_id_token_metadata        text          DEFAULT NULL,
    refresh_token_value           text          DEFAULT NULL,
    refresh_token_issued_at       timestamp     DEFAULT NULL,
    refresh_token_expires_at      timestamp     DEFAULT NULL,
    refresh_token_metadata        text          DEFAULT NULL,
    user_code_value               text          DEFAULT NULL,
    user_code_issued_at           timestamp     DEFAULT NULL,
    user_code_expires_at          timestamp     DEFAULT NULL,
    user_code_metadata            text          DEFAULT NULL,
    device_code_value             text          DEFAULT NULL,
    device_code_issued_at         timestamp     DEFAULT NULL,
    device_code_expires_at        timestamp     DEFAULT NULL,
    device_code_metadata          text          DEFAULT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE oauth2_authorization_consent
(
    registered_client_id varchar(100)  NOT NULL,
    principal_name       varchar(200)  NOT NULL,
    authorities          varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);


-- Create Users Table
CREATE TABLE sys_user
(
    id                      VARCHAR(50)  NOT NULL PRIMARY KEY,
    username                VARCHAR(255) NOT NULL,
    password                VARCHAR(255) NOT NULL,
    name                    VARCHAR(255),                        -- full name
    nickname                VARCHAR(255),
    gender                  VARCHAR(10),                         -- 'male', 'female', etc.
    birthdate               DATE,                                -- date of birth
    avatar_name              VARCHAR(255),                        -- name of profile picture
    email                   VARCHAR(255) UNIQUE,
    email_verified          BOOLEAN               DEFAULT FALSE, -- is email verified
    email_token             VARCHAR(255),
    password_token          VARCHAR(255),
    phone_number            VARCHAR(20),
    phone_number_verified   BOOLEAN               DEFAULT FALSE, -- is phone number verified

    account_non_expired     BOOLEAN      NOT NULL DEFAULT true,
    account_non_locked      BOOLEAN      NOT NULL DEFAULT true,
    credentials_non_expired BOOLEAN      NOT NULL DEFAULT true,
    enabled                 BOOLEAN               DEFAULT TRUE,
    mfa                     BOOLEAN      NOT NULL DEFAULT TRUE,
    system                  BOOLEAN      NOT NULL DEFAULT FALSE,
    created_by              VARCHAR(50),
    updated_by              VARCHAR(50),
    created_at              TIMESTAMP             DEFAULT current_timestamp,
    updated_at              TIMESTAMP             DEFAULT current_timestamp
);

CREATE TABLE password_reset
(
    id         VARCHAR(50)  NOT NULL PRIMARY KEY,
    user_id    VARCHAR(50)  NOT NULL,
    token      VARCHAR(255) NOT NULL,
    sent       BOOLEAN      NOT NULL DEFAULT FALSE,
    expired_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    created_at TIMESTAMP             DEFAULT current_timestamp,
    updated_at TIMESTAMP             DEFAULT current_timestamp,
    FOREIGN KEY (user_id) REFERENCES sys_user (id) ON DELETE CASCADE
);

CREATE TABLE email_verification
(
    id         VARCHAR(50)  NOT NULL PRIMARY KEY,
    user_id    VARCHAR(50)  NOT NULL,
    token      VARCHAR(255) NOT NULL,
    sent       BOOLEAN      NOT NULL DEFAULT FALSE,
    expired_at TIMESTAMP    NOT NULL,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    created_at TIMESTAMP             DEFAULT current_timestamp,
    updated_at TIMESTAMP             DEFAULT current_timestamp,
    FOREIGN KEY (user_id) REFERENCES sys_user (id) ON DELETE CASCADE
);

-- Create Roles Table
CREATE TABLE sys_role
(
    id          VARCHAR(50)         NOT NULL PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255),
    system      BOOLEAN             NOT NULL DEFAULT FALSE,
    created_by  VARCHAR(50),
    updated_by  VARCHAR(50),
    created_at  TIMESTAMP                    DEFAULT current_timestamp,
    updated_at  TIMESTAMP                    DEFAULT current_timestamp
);

-- Create Permissions Table
CREATE TABLE sys_authority
(
    id          VARCHAR(50)         NOT NULL PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255),
    system      BOOLEAN             NOT NULL DEFAULT FALSE,
    created_by  VARCHAR(50),
    updated_by  VARCHAR(50),
    created_at  TIMESTAMP                    DEFAULT current_timestamp,
    updated_at  TIMESTAMP                    DEFAULT current_timestamp
);

-- Create Groups Table
CREATE TABLE sys_group
(
    id          VARCHAR(50)         NOT NULL PRIMARY KEY,
    parent_id   VARCHAR(50),
    name        VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255),
    system      BOOLEAN             NOT NULL DEFAULT FALSE,
    created_by  VARCHAR(50),
    updated_by  VARCHAR(50),
    created_at  TIMESTAMP                    DEFAULT current_timestamp,
    updated_at  TIMESTAMP                    DEFAULT current_timestamp
);

-- Create UserGroups Table
CREATE TABLE sys_user_group
(
    user_id    VARCHAR(50) NOT NULL,
    group_id   VARCHAR(50) NOT NULL,
    system     BOOLEAN     NOT NULL DEFAULT FALSE,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    created_at TIMESTAMP            DEFAULT current_timestamp,
    updated_at TIMESTAMP            DEFAULT current_timestamp,
    PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES sys_user (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES sys_group (id) ON DELETE CASCADE
);

-- Create GroupRoles Table
CREATE TABLE sys_group_role
(
    group_id   VARCHAR(50) NOT NULL,
    role_id    VARCHAR(50) NOT NULL,
    system     BOOLEAN     NOT NULL DEFAULT FALSE,
    created_by VARCHAR(50),
    updated_by VARCHAR(50),
    created_at TIMESTAMP            DEFAULT current_timestamp,
    updated_at TIMESTAMP            DEFAULT current_timestamp,
    PRIMARY KEY (group_id, role_id),
    FOREIGN KEY (group_id) REFERENCES sys_group (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES sys_role (id) ON DELETE CASCADE
);

-- Create RoleAuthority Table
CREATE TABLE sys_role_authority
(
    role_id      VARCHAR(50) NOT NULL,
    authority_id VARCHAR(50) NOT NULL,
    created_by   VARCHAR(50),
    updated_by   VARCHAR(50),
    created_at   TIMESTAMP DEFAULT current_timestamp,
    updated_at   TIMESTAMP DEFAULT current_timestamp,
    PRIMARY KEY (role_id, authority_id),
    FOREIGN KEY (role_id) REFERENCES sys_role (id) ON DELETE CASCADE,
    FOREIGN KEY (authority_id) REFERENCES sys_authority (id) ON DELETE CASCADE
);

CREATE TABLE uploaded_file
(
    id                 VARCHAR(50)  NOT NULL PRIMARY KEY,
    original_file_name VARCHAR(255) NOT NULL,
    extension          VARCHAR(255) NOT NULL,
    size               BIGINT       NOT NULL,
    url                TEXT,
    upload_at          TIMESTAMP DEFAULT current_timestamp,
    created_by         VARCHAR(50),
    updated_by         VARCHAR(50),
    created_at         TIMESTAMP DEFAULT current_timestamp,
    updated_at         TIMESTAMP DEFAULT current_timestamp
);