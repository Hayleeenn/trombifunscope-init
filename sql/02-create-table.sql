SET ROLE admin;

-- Enums
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'pokemon_type') THEN
        CREATE TYPE pokemon_type
        AS ENUM (
            'Acier', 'Combat', 'Dragon', 'Eau', 'Electrik', 'Fee', 'Feu', 'Glace', 'Insecte',
            'Normal', 'Plante', 'Poison', 'Psy', 'Roche', 'Sol', 'Spectre', 'Tenebres', 'Vol',
            'Basketator', 'Calinou', 'Faignant', 'Hibou', 'Intelo', 'Machetero',
            'Patriote', 'Phantom', 'Sherlock'
        );
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'pokemon_height') THEN
        CREATE TYPE pokemon_height AS ENUM ('XXL', 'XL', 'L', 'M', 'S', 'XS');
    END IF;
END $$;

-- Table
CREATE TABLE IF NOT EXISTS t_student (
   id                   bigint        GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   first_name           VARCHAR(30)   NOT NULL,
   pokemon_name         VARCHAR(30)   NOT NULL,
   level                SMALLINT,
   type                 pokemon_type  NOT NULL,
   health_point         SMALLINT,                   -- Changement
   height               pokemon_height,             -- Changement
   is_flying            BOOLEAN       NOT NULL,
   evolution            VARCHAR(30)   UNIQUE,
   natural_environment  VARCHAR(40)   NOT NULL,
   gift                 VARCHAR(50),
   gift_cost            SMALLINT      CHECK (gift_cost BETWEEN 1 AND 3),
   gift_damage          SMALLINT,
   gift_text            VARCHAR(120),
   strength             VARCHAR(20)   NOT NULL,
   strength_cost        SMALLINT      CHECK (strength_cost BETWEEN 1 AND 3),
   strength_damage      SMALLINT,
   strength_text        VARCHAR(120),
   weakness             VARCHAR(20)   NOT NULL,
   quote                VARCHAR(100),
   catch_date           DATE,
   avatar_url           VARCHAR(200)  NOT NULL UNIQUE
);

GRANT SELECT, INSERT, UPDATE, DELETE ON t_student TO sacha;
