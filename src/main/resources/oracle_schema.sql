CREATE TABLE user_lab
(
  username VARCHAR (55) NOT NULL,
  name VARCHAR (255),
  second_name VARCHAR (255),
  email VARCHAR (100),
  password VARCHAR(70) NOT NULL ,
  enabled NUMBER(1) DEFAULT 1,
  PRIMARY KEY (username)
);

CREATE TABLE authorities (
  authority_id numeric(11) NOT NULL,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  CONSTRAINT uni_username_role UNIQUE (role,username),
  CONSTRAINT authorities_pk PRIMARY KEY (authority_id),
  CONSTRAINT fk_username
    FOREIGN KEY (username)
    REFERENCES user_lab (username)
);

CREATE TABLE note_lab (
  id number(19) PRIMARY KEY,
  title VARCHAR (255) ,
  content VARCHAR (255),
  date_creation TIMESTAMP ,
  date_edit TIMESTAMP
);

CREATE TABLE note (
  id number(19) PRIMARY KEY,
  title VARCHAR (255) ,
  content VARCHAR (255),
);

CREATE SEQUENCE authority_seq START WITH 1;

CREATE OR REPLACE TRIGGER authority_id
BEFORE INSERT ON authorities
FOR EACH ROW

BEGIN
  SELECT authority_seq.NEXTVAL
  INTO   :new.authority_id
  FROM   dual;
END;


CREATE SEQUENCE note_seq START WITH 1;

CREATE OR REPLACE TRIGGER note_id
BEFORE INSERT ON note_lab
FOR EACH ROW

BEGIN
  SELECT note_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;


