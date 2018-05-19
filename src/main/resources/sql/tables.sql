CREATE DATABASE school_app;

CREATE TABLE invite (
  id SERIAL NOT NULL,
  code TEXT NOT NULL UNIQUE,
  is_used BOOLEAN DEFAULT false,
  role VARCHAR(10) NOT NULL,
  CONSTRAINT PK_invite PRIMARY KEY (id)
);

CREATE TABLE users (
  id SERIAL NOT NULL,
  login VARCHAR(20) NOT NULL UNIQUE,
  password TEXT NOT NULL,
  email VARCHAR(40),
  phone_number VARCHAR(20),
  role VARCHAR(10) NOT NULL,
  name VARCHAR(20) NOT NULL,
  surname VARCHAR(30) NOT NULL,
  patronymic VARCHAR(20),
  CONSTRAINT PK_user PRIMARY KEY (id)
);

CREATE TABLE class (
  id SERIAL NOT NULL,
  first_year INTEGER NOT NULL,
  letter VARCHAR(5),
  CONSTRAINT PK_class PRIMARY KEY (id)
);

CREATE TABLE subject (
  id SERIAL NOT NULL,
  name VARCHAR(20),
  CONSTRAINT PK_subject PRIMARY KEY (id)
);

CREATE TABLE lesson_number_time (
  lesson_number INTEGER NOT NULL,
  start_time VARCHAR(5) NOT NULL,
  end_time VARCHAR(5) NOT NULL,
  CONSTRAINT PK_lesson_number_time PRIMARY KEY (lesson_number)
);

CREATE TABLE lesson (
  id SERIAL NOT NULL,
  subject_id INTEGER NOT NULL,
  class_id INTEGER NOT NULL,
  teacher_id INTEGER NOT NULL,
  weekday INTEGER NOT NULL,
  number INTEGER NOT NULL,
  room VARCHAR(15) NOT NULL,
  CONSTRAINT PK_lesson PRIMARY KEY (id),
  CONSTRAINT FK_subject FOREIGN KEY (subject_id) REFERENCES subject (id),
  CONSTRAINT FK_teacher FOREIGN KEY (teacher_id) REFERENCES users (id),
  CONSTRAINT FK_class FOREIGN KEY (class_id) REFERENCES class (id),
  CONSTRAINT FK_number_time FOREIGN KEY (number) REFERENCES lesson_number_time (lesson_number)
);

CREATE TABLE mark (
  id SERIAL NOT NULL,
  user_id INTEGER NOT NULL,
  lesson_id INTEGER NOT NULL,
  value VARCHAR(1) NOT NULL,
  date DATE NOT NULL,
  CONSTRAINT PK_mark PRIMARY KEY (id),
  CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT FK_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id),
  CONSTRAINT CK_value CHECK (value IN ('1', '2', '3', '4', '5', 'н', 'б'))
);

CREATE TABLE homework (
  id SERIAL NOT NULL,
  lesson_id INTEGER NOT NULL,
  text TEXT NOT NULL,
  date DATE NOT NULL,
  CONSTRAINT PK_homework PRIMARY KEY (id),
  CONSTRAINT FK_lesson FOREIGN KEY (lesson_id) REFERENCES lesson (id)
);

CREATE TABLE student_info (
  user_id INTEGER NOT NULL,
  class_id INTEGER NOT NULL,
  CONSTRAINT PK_student_info PRIMARY KEY (user_id),
  CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT FK_class FOREIGN KEY (class_id) REFERENCES class (id)
);
