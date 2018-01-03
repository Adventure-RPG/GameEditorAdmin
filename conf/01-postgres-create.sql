DROP TABLE IF EXISTS "USER",CATEGORY_TYPE, MODEL, GENDER, RACE_OPTION, RACE_OPTION_GENDER, RACE_OPTION_MODEL, GENDER_MODEL, RACE, SCRIPT, SKILL, CHARACTERISTIC, CHARACTER, CHARACTER_SKILL CASCADE;


CREATE TABLE "USER" (
  id                       SERIAL PRIMARY KEY,
  login                    VARCHAR(100) not null,
  password                 VARCHAR(100) not null,
  email                    VARCHAR(100),
  is_enabled               BOOLEAN not null DEFAULT TRUE,
  first_name               VARCHAR(100),
  last_name                VARCHAR(100),

  -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0,

  CONSTRAINT USER_LOGIN_UQ UNIQUE (login)
);
COMMENT ON COLUMN "USER"."login" is 'The login used to login';


CREATE TABLE CHARACTERISTIC (
  id  SERIAL PRIMARY KEY,
  strength INTEGER NOT NULL,
  agility INTEGER NOT NULL,
  endurance INTEGER NOT NULL,
  intelligence INTEGER NOT NULL,
  charisma INTEGER NOT NULL,
  perception INTEGER NOT NULL,
  luck INTEGER NOT NULL,

  points_available  INTEGER NOT NULL DEFAULT 0,
  skill_available INTEGER NOT NULL DEFAULT 0,


    -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0
);


CREATE TABLE CATEGORY_TYPE (
  id  SERIAL PRIMARY KEY,
  name  VARCHAR(150) NOT NULL,
  description  TEXT,
  cat_id  INTEGER,

    -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0,

  CONSTRAINT CATEGORY_TYPE_NAME_UQ UNIQUE (name),

  FOREIGN KEY (cat_id) REFERENCES CATEGORY_TYPE(id)
);


CREATE TABLE GENDER (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200),

  strengthMin INTEGER,
  strengthMax INTEGER,
  agilityMin INTEGER,
  agilityMax INTEGER,
  enduranceMin INTEGER,
  enduranceMax INTEGER,
  intelligenceMin INTEGER,
  intelligenceMax INTEGER,
  charismaMin INTEGER,
  charismaMax INTEGER,
  perceptionMin INTEGER,
  perceptionMax INTEGER,
  luckMin INTEGER,
  luckMax INTEGER,

  heightMin INTEGER,
  heightMax INTEGER,
  weightMin INTEGER,
  weightMax INTEGER,

  -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0,

  CONSTRAINT GENDER_NAME_UQ UNIQUE (name)
);


CREATE TABLE RACE_OPTION (
  id  SERIAL PRIMARY KEY,

  strengthMin INTEGER NOT NULL,
  strengthMax INTEGER NOT NULL,
  agilityMin INTEGER NOT NULL,
  agilityMax INTEGER NOT NULL,
  enduranceMin INTEGER NOT NULL,
  enduranceMax INTEGER NOT NULL,
  intelligenceMin INTEGER NOT NULL,
  intelligenceMax INTEGER NOT NULL,
  charismaMin INTEGER NOT NULL,
  charismaMax INTEGER NOT NULL,
  perceptionMin INTEGER NOT NULL,
  perceptionMax INTEGER NOT NULL,
  luckMin INTEGER NOT NULL,
  luckMax INTEGER NOT NULL,

  heightMin INTEGER NOT NULL,
  heightMax INTEGER NOT NULL,
  weightMin INTEGER NOT NULL,
  weightMax INTEGER NOT NULL,

    -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0
);


CREATE TABLE MODEL (
  id  SERIAL PRIMARY KEY,
  name  VARCHAR(150) NOT NULL,
  file_name VARCHAR(300) NOT NULL,
  cat_id INTEGER NOT NULL,
  color VARCHAR(50),

  -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0,


  CONSTRAINT MODEL_NAME_UQ UNIQUE (name),

  FOREIGN KEY (cat_id) REFERENCES CATEGORY_TYPE(id)
);

CREATE TABLE RACE_OPTION_GENDER (
  race_opt_id INTEGER not null,
  gender_id INTEGER not NULL,

  FOREIGN KEY (race_opt_id) REFERENCES RACE_OPTION(id),
  FOREIGN KEY (gender_id) REFERENCES GENDER(id),
  PRIMARY KEY (race_opt_id, gender_id)
);


CREATE TABLE GENDER_MODEL (
  gender_id INTEGER not NULL,
  model_id INTEGER NOT NULL,

  FOREIGN KEY (gender_id) REFERENCES RACE_OPTION(id),
  FOREIGN KEY (model_id) REFERENCES MODEL(id),
  PRIMARY KEY (gender_id, model_id)
);

CREATE TABLE RACE (
  id  SERIAL PRIMARY KEY,
  name  VARCHAR(200) NOT NULL,
  description TEXT,
  img_file  VARCHAR(300) NOT NULL,

  charc_id  INTEGER NOT NULL,
  race_opt_id  INTEGER NOT NULL,

  -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0,


  FOREIGN KEY (charc_id) REFERENCES CHARACTERISTIC(id),
  FOREIGN KEY (race_opt_id) REFERENCES RACE_OPTION(id)
);


CREATE TABLE SCRIPT (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200) not NULL,
  file_name VARCHAR(300) NOT NULL,
  arguments VARCHAR(500),

    -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0,

  CONSTRAINT SCRIPT_NAME_UQ UNIQUE (name)
);


CREATE TABLE SKILL (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  skill_id INTEGER,
  position BOOLEAN NOT NULL DEFAULT FALSE,
  script_id INTEGER NOT NULL,
  on_init BOOLEAN NOT NULL DEFAULT FALSE,


  -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0,

  CONSTRAINT SKILL_NAME_UQ UNIQUE (name),

  FOREIGN KEY (skill_id) REFERENCES SKILL(id),
  FOREIGN KEY (script_id) REFERENCES SCRIPT(id)
);


CREATE TABLE CHARACTER (
  id SERIAL PRIMARY KEY,
  username VARCHAR(200) NOT NULL,

  race_id INTEGER NOT NULL,
  charc_id INTEGER NOT NULL,

    -- audit (detected by celerio by convention)
  creation_date            timestamp default current_timestamp,
  creation_author          varchar(200),
  last_modification_date   timestamp default current_timestamp,
  last_modification_author varchar(200),

  -- optimistic lock (detected by celerio by convention)
  version                  INTEGER default 0,


  CONSTRAINT CHARACTER_USERNAME_UQ UNIQUE (username),
  CONSTRAINT CHARACTER_CHARS_UQ UNIQUE (charc_id),

  FOREIGN KEY (race_id) REFERENCES RACE(id),
  FOREIGN KEY (charc_id) REFERENCES CHARACTERISTIC(id)
);


CREATE TABLE CHARACTER_SKILL (
  char_id INTEGER not null,
  skill_id INTEGER not NULL,

  FOREIGN KEY (char_id) REFERENCES CHARACTER(id),
  FOREIGN KEY (skill_id) REFERENCES SKILL(id),
  PRIMARY KEY (char_id, skill_id)
);

INSERT INTO "USER" (id, login, password, email, is_enabled, version) VALUES (-1, 'admin', 'admin', 'admin@example.com', true, 1);

INSERT INTO CHARACTERISTIC (strength, agility, endurance, intelligence, charisma, perception, luck, version) VALUES (5, 4, 4, 4, 4, 4, 5, 1);

INSERT INTO RACE_OPTION (strengthMin, strengthMax, agilityMin, agilityMax, enduranceMin, enduranceMax, intelligenceMin, intelligenceMax, charismaMin, charismaMax, perceptionMin, perceptionMax, luckMin, luckMax, heightMin, heightMax, weightMin, weightMax) VALUES (5, 30, 5, 30, 5, 30, 5, 30, 5, 30, 5, 30, 5, 30, 110, 220, 35, 150);

INSERT INTO CATEGORY_TYPE(id, name, description) VALUES (-1, 'TEST', 'THIS IS A TEST CATEGORY');

INSERT INTO MODEL(id, name, file_name, cat_id, color) VALUES (-1, 'TEST_MODEL', 'TEST_MODEL.FDX', -1, '#FFFFFF');

INSERT INTO GENDER(name) VALUES ('MALE');
INSERT INTO GENDER(name) VALUES ('FEMALE');

INSERT INTO SCRIPT(id, name, file_name) VALUES (-1, 'TEST_NAME', 'TEST_SCRIPT.JAR');

INSERT INTO SKILL(id, name, script_id) VALUES (-1, 'TEST_SKILL', -1);
