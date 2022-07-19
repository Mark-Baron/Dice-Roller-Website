BEGIN TRANSACTION;

DROP TABLE IF EXISTS roller_user, character, character_skill, skill;

DROP SEQUENCE IF EXISTS seq_user_id, seq_character_id, seq_skill_id;

CREATE SEQUENCE seq_user_id
	INCREMENT BY 1
	START WITH 1
	NO MAXVALUE;
	
CREATE TABLE roller_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_roller_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE SEQUENCE seq_character_id
  INCREMENT BY 1
  START WITH 1000
  NO MAXVALUE;
  
CREATE TABLE character (
	character_id int NOT NULL DEFAULT nextval('seq_character_id'),
	user_id int NOT NULL,
	name varchar(50) NOT NULL,
	lvl int NOT NULL,
	class varchar(15) NOT NULL,
	strength_score int NOT NULL,
	dexterity_score int NOT NULL,
	constitution_score int NOT NULL,
	intelligence_score int NOT NULL,
	wisdom_score int NOT NULL,
	charisma_score int NOT NULL,
	CONSTRAINT PK_character PRIMARY KEY (character_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES roller_user(user_id),
	CONSTRAINT CK_strength_score CHECK (strength_score >= 1 AND strength_score <= 20),
	CONSTRAINT CK_dexterity_score CHECK (dexterity_score >= 1 AND dexterity_score <= 20),
	CONSTRAINT CK_constitution_score CHECK (constitution_score >= 1 AND constitution_score <= 20),
	CONSTRAINT CK_intelligence_score CHECK (intelligence_score >= 1 AND intelligence_score <= 20),
	CONSTRAINT CK_wisdom_score CHECK (wisdom_score >= 1 AND wisdom_score <= 20),
	CONSTRAINT CK_charisma_score CHECK (charisma_score >= 1 AND charisma_score <= 20)
);

CREATE SEQUENCE seq_skill_id
	INCREMENT BY 1
	START WITH 2000
	NO MAXVALUE;
	
CREATE TABLE skill (
	skill_id int NOT NULL DEFAULT nextval('seq_skill_id'),
	skill_name varchar(25) NOT NULL,
	CONSTRAINT PK_skill PRIMARY KEY (skill_id)
);

CREATE TABLE character_skill (
	character_id int NOT NULL,
	skill_id int NOT NULL,
	CONSTRAINT PK_character_skill PRIMARY KEY (character_id, skill_id),
	CONSTRAINT FK_character_id FOREIGN KEY (character_id) REFERENCES character(character_id),
	CONSTRAINT FK_skill_id FOREIGN KEY (skill_id) REFERENCES skill(skill_id)
);

INSERT INTO skill (skill_name)
VALUES ('acrobatics'),
('animal handling'),
('arcana'),
('athletics'),
('deception'),
('history'),
('insight'),
('intimidation'),
('investigation'),
('medicine'),
('nature'),
('perception'),
('performance'),
('persuasion'),
('religion'),
('sleight of hand'),
('stealth'),
('survival');

COMMIT;
	