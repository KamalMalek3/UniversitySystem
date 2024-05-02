CREATE TABLE ids (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  StudentId INT,
  TeacherId INT
);
-- Create the Students table with proper primary key naming
CREATE TABLE Students (
  Student_id INT PRIMARY KEY,  -- Consider removing this column if not used
  fullname VARCHAR(255),
  Student_Password VARCHAR(255),
  INDEX (student_id)  -- Index on student_id for potential foreign key usage
);
-- Create the Teachers table with an index on Teacher_id for foreign key
CREATE TABLE Teachers (
  Teacher_id INT PRIMARY KEY,
  fullname VARCHAR(255),
  Teacher_Password VARCHAR(255),
  INDEX (Teacher_id)
);
-- Create the course table with a foreign key referencing Teachers(Teacher_id)
CREATE TABLE course (
  Course_code VARCHAR(255) PRIMARY KEY,
  Course_Name VARCHAR(255),
  credits INT,
  Teacher_id INT,
  INDEX (Course_code),
  FOREIGN KEY (Teacher_id) REFERENCES Teachers(Teacher_id)
);

-- Create the enrollments table with foreign keys
CREATE TABLE enrollments (
  Student_id INT,
  course_code VARCHAR(255),
  PRIMARY KEY (Student_id, course_code),
  FOREIGN KEY (Student_id) REFERENCES Students(student_id),
  FOREIGN KEY (course_code) REFERENCES course(Course_code)
);

-- Create the grades table with foreign keys
CREATE TABLE grades (
  student_id INT,
  course_code VARCHAR(255),
  grade INT,
  PRIMARY KEY (Student_id, course_code),
  FOREIGN KEY (student_id) REFERENCES Students(student_id),
  FOREIGN KEY (course_code) REFERENCES course(Course_code)
);

INSERT INTO ids (StudentId, TeacherId) VALUES (1, 1);