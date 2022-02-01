-- Users

CREATE TABLE courses.users (
id INT NOT NULL AUTO_INCREMENT,
password VARCHAR(200) NOT NULL,
login VARCHAR(100) NOT NULL,
reg_date DATE,
photo BLOB,

CONSTRAINT user_pk PRIMARY KEY (id)
 
);

-- Student

CREATE TABLE courses.students (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
user_id INT,
address VARCHAR(200),
email VARCHAR(200),
phone VARCHAR(20),

CONSTRAINT student_pk PRIMARY KEY (id),

CONSTRAINT student_user_fk FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE
ON UPDATE CASCADE
 
);

-- Admin

CREATE TABLE courses.admins (
id INT NOT NULL AUTO_INCREMENT,
user_id INT,
name VARCHAR(100) NOT NULL,
address VARCHAR(200),
email VARCHAR(200),
phone VARCHAR(20),
position VARCHAR(50),

CONSTRAINT admin_pk PRIMARY KEY (id),

CONSTRAINT admin_user_fk FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE
ON UPDATE CASCADE
 
);

-- Contact

CREATE TABLE courses.contacts (
id INT NOT NULL AUTO_INCREMENT,
student_id INT,
name VARCHAR(100) NOT NULL,
email VARCHAR(200),
phone VARCHAR(20),
message VARCHAR(250),

CONSTRAINT contact_pk PRIMARY KEY (id),

CONSTRAINT contact_student_fk FOREIGN KEY (student_id)
REFERENCES students(id)
ON DELETE RESTRICT
ON UPDATE RESTRICT
);

-- Course

CREATE TABLE courses.courses(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(200) NOT NULL,
resources BLOB,
description VARCHAR(500),
fee NUMERIC,

CONSTRAINT course_pk PRIMARY KEY (id)
);

-- feedback

CREATE TABLE courses.feedbacks (
id INT NOT NULL AUTO_INCREMENT,
student_id INT,
name VARCHAR(100) NOT NULL,
email VARCHAR(200),
feedback VARCHAR(250),

CONSTRAINT feedback_pk PRIMARY KEY (id),

CONSTRAINT feedback_student_fk FOREIGN KEY (student_id)
REFERENCES students(id)
ON DELETE RESTRICT
ON UPDATE RESTRICT
);

-- roles

CREATE TABLE courses.roles(
	id INT NOT NULL AUTO_INCREMENT,
    role VARCHAR(20) NOT NULL,
    
    CONSTRAINT role_pk PRIMARY KEY (id)
);

-- user_roles

CREATE TABLE courses.user_roles(
	user_id INT NOT NULL, 
    role_id INT NOT NULL, 
    CONSTRAINT user_role_pk PRIMARY KEY (user_id, role_id),
    
    CONSTRAINT user_roles_user_fk FOREIGN KEY (user_id)
	REFERENCES users(id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,

	CONSTRAINT user_roles_role_fk FOREIGN KEY (role_id)
	REFERENCES roles(id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
);

-- student_courses

CREATE TABLE courses.student_courses(
    student_id INT NOT NULL, 
    course_id INT NOT NULL, 
    CONSTRAINT student_course_pk PRIMARY KEY (student_id, course_id),
    
    CONSTRAINT student_courses_student_fk FOREIGN KEY (student_id)
	REFERENCES students(id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT,

	CONSTRAINT student_courses_course_fk FOREIGN KEY (course_id)
	REFERENCES courses(id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
);