INSERT INTO students (firstName, lastName, dateOfBirth, jmbag, ects)
VALUES ('Marko', 'Marković', '2000-01-01', '1234567890', 120);

INSERT INTO students (firstName, lastName, dateOfBirth, jmbag, ects)
VALUES ('Iva', 'Ivić', '1995-02-02', '2345678901', 180);

INSERT INTO students (firstName, lastName, dateOfBirth, jmbag, ects)
VALUES ('Ana', 'Anić', '2001-03-03', '3456789012', 300);

INSERT INTO courses (name, ects_points) VALUES ('Programiranje', 6);
INSERT INTO courses (name, ects_points) VALUES ('Programiranje u jeziku Java', 5);
INSERT INTO courses (name, ects_points) VALUES ('Objektno orijentirano programiranje', 5);

INSERT INTO course_students (student_jmbag, course_id) VALUES ('1234567890', 1);
INSERT INTO course_students (student_jmbag, course_id) VALUES ('1234567890', 2);
INSERT INTO course_students (student_jmbag, course_id) VALUES ('2345678901', 2);
INSERT INTO course_students (student_jmbag, course_id) VALUES ('3456789012', 1);

insert into user (id, username, password, first_name, last_name)
values (1, 'admin', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy', 'admin', 'admin');
insert into user (id, username, password, first_name, last_name)
values (2, 'user', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy', 'user', 'user');

insert into authority (id, name)
values (1, 'ROLE_ADMIN');
insert into authority (id, name)
values (2, 'ROLE_USER');

insert into user_authority (user_id, authority_id)
values (1, 1);
insert into user_authority (user_id, authority_id)
values (2, 2);