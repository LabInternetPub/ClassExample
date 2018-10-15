
INSERT INTO note_lab (title, content, date_creation, date_edit) VALUES ('spring', 'va super be', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO note_lab (title, content, date_creation, date_edit) VALUES ('spring boot', 'va encara millor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO note_lab (title, content, date_creation, date_edit) VALUES ('Laboratori app inernet', 'és la millor assignatura del món mundial', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_lab VALUES ('roure', 'Josep', 'Roure', 'roure@tecnocampus.cat', '{noop}roure', 1);
INSERT INTO user_lab VALUES ('alvarez', 'Sergi', 'Alvarez', 'alvarez@mail.cat', '{noop}alvarez', 1);
INSERT INTO user_lab VALUES ('castells', 'Esther', 'Castells', 'castells@mail.cat', '{noop}castells', 1);
INSERT INTO user_lab VALUES ('riera', 'Joana', 'Riera', 'riera@mail.cat', '{noop}riera', 1);
INSERT INTO user_lab VALUES ('garcia', 'Marcel', 'Garcia', 'garcia@mail.cat', '{noop}garcia', 1);
INSERT INTO user_lab VALUES ('lecina', 'Maria', 'Lecina', 'lecina@mail.cat', '{noop}lecina', 1);

INSERT INTO authorities (username, role) VALUES ('roure', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('roure', 'ROLE_ADMIN');
INSERT INTO authorities (username, role) VALUES ('alvarez', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('castells', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('riera', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('garcia', 'ROLE_USER');
INSERT INTO authorities (username, role) VALUES ('lecina', 'ROLE_USER');
