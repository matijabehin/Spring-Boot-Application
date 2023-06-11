CREATE TABLE IF NOT EXISTS students(
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    dateOfBirth DATE,
    jmbag VARCHAR(10) PRIMARY KEY,
    ects INT
);

CREATE TABLE IF NOT EXISTS courses(
    courseID BIGINT AUTO_INCREMENT,
    name VARCHAR(255),
    ects_points INT,
    PRIMARY KEY (courseID)
);

CREATE TABLE IF NOT EXISTS course_students (
    student_jmbag VARCHAR(10),
    course_id BIGINT,
    PRIMARY KEY (student_jmbag, course_id),
    FOREIGN KEY (student_jmbag) REFERENCES students(jmbag),
    FOREIGN KEY (course_id) REFERENCES courses(courseID)
);

create table if not exists user (
    id bigint primary key auto_increment,
    username varchar(100),
    password varchar(250),
    first_name varchar(250),
    last_name varchar(250)
);

create table if not exists authority (
    id bigint auto_increment,
    name varchar(100),
    primary key (id)
);



CREATE TABLE IF NOT EXISTS user_authority (
    user_id BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, authority_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_authority FOREIGN KEY (authority_id) REFERENCES authority(id)
);