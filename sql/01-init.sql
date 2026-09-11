-- As Admin
SET ROLE admin;

-- Role admin
DO $$
    BEGIN
        -- Si le rôle n'existe pas
        IF NOT EXISTS (
            SELECT 1
            FROM pg_roles
            WHERE rolname = 'admin'
        ) THEN
            -- on créé le rôle
            CREATE ROLE admin WITH
                LOGIN 
                PASSWORD 'Meyer75?';
        END IF;
    COMMIT;
END $$;

SELECT 'CREATE DATABASE "trombifunscope" WITH OWNER = admin ENCODING = ''UTF8'''
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'trombifunscope');

-- Role applicatif
DO $$
    BEGIN
        -- Si le rôle n'existe pas
        IF NOT EXISTS (
            SELECT 1
            FROM pg_roles
            WHERE rolname = 'sacha'
        ) THEN
            -- on créé le rôle
            CREATE ROLE sacha WITH
                LOGIN 
                PASSWORD 'Meyer75?';
        END IF;
    COMMIT;
END $$;

\c trombifunscope;

SET ROLE admin;

GRANT CONNECT ON DATABASE trombifunscope TO sacha;