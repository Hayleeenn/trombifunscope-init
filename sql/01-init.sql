
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
                PASSWORD 'Meyer75?'
                CREATEDB
                CREATEROLE;
        END IF;
END $$;

-- As Admin
GRANT admin TO postgres;
SET ROLE admin;

SELECT 'CREATE DATABASE "trombifunscope" WITH OWNER = admin ENCODING = ''UTF8'''
WHERE NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'trombifunscope')\gexec

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
END $$;


-- Revoque les droits par défaut sur la base de données et accorde la connnexion à sacha
REVOKE ALL ON DATABASE trombifunscope FROM PUBLIC;
GRANT CONNECT ON DATABASE trombifunscope TO sacha;

\c trombifunscope;

SET ROLE admin;

-- Personne ne peut crée d'objet dans le schéma public, sauf admin et le superutilisateur
REVOKE CREATE ON SCHEMA public FROM PUBLIC;
GRANT USAGE ON SCHEMA public TO sacha;

-- Sacha peut lire et écrire dans toutes les tables que créera admin, rien de plus
ALTER DEFAULT PRIVILEGES FOR ROLE admin IN SCHEMA public
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO sacha;
