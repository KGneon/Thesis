DROP DATABASE IF EXISTS thesis_db;

CREATE DATABASE thesis_db;

USE thesis_db;

-- Create the Promoter table
CREATE TABLE Promoter (
    promoter_id INT AUTO_INCREMENT  PRIMARY KEY,
    promoter_name VARCHAR(255) NOT NULL,
    promoter_surname VARCHAR(255) NOT NULL,
    field VARCHAR(255) NOT NULL,
    number_of_students_lead INT
);

-- Create the Thesis table
CREATE TABLE Thesis (
	thesis_id INT AUTO_INCREMENT  PRIMARY KEY,
	thesis_name VARCHAR(50) NOT NULL,
	thesis_field VARCHAR(50) NOT NULL,
	thesis_type VARCHAR(50) NOT NULL
);

-- Create the Student table
CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(50) NOT NULL,
    student_surname VARCHAR(255) NOT NULL,
    thesis_id INT,
    promoter_id INT,
 
    FOREIGN KEY (thesis_id) REFERENCES Thesis (thesis_id),
	FOREIGN KEY (promoter_id) REFERENCES Promoter (promoter_id)

);



-- Sample Promotor records
INSERT INTO Promoter (promoter_id, promoter_name, promoter_surname, field, number_of_students_lead)
VALUES
    (101, 'Prof. John', 'Smith', 'Computer Science', 5),
    (102, 'Dr. Alice', 'Johnson', 'Mathematics', 4),
    (103, 'Prof. Michael', 'Brown', 'Physics', 6),
    (104, 'Dr. Sarah', 'Lee', 'Biology', 3),
    (105, 'Prof. David', 'Williams', 'Chemistry', 7),
    (106, 'Dr. Laura', 'Garcia', 'Engineering', 5),
    (107, 'Prof. Robert', 'Davis', 'Economics', 2),
    (108, 'Dr. Emily', 'Clark', 'Psychology', 4);

-- Sample Thesis records
INSERT INTO Thesis (thesis_id, thesis_name, thesis_field, thesis_type)
VALUES
	(1001, 'A Comparative Study of Machine Learning Algorithms', 'Computer Science', 'BACHELOR'),
	(1002, 'Advances in Number Theory', 'Mathematics', 'MASTER'),
	(1003, 'Quantum Mechanics and its Applications', 'Physics', 'ENGINEER'),
	(1004, 'Genome Sequencing and Analysis', 'Biology', 'DOCTOR'),
	(1005, 'Green Chemistry: Sustainable Approaches', 'Chemistry', 'MASTER'),
	(1006, 'Design and Analysis of Composite Materials', 'Engineering', 'DOCTOR'),
	(1007, 'Behavioral Economics in Decision Making', 'Economics', 'BACHELOR'),
	(1008, 'The Influence of Social Media on Mental Health', 'Psychology', 'MASTER'),
	(1009, 'Secure Software Development Practices', 'Computer Science', 'BACHELOR'),
	(1010, 'Exploring Riemann Hypothesis', 'Mathematics', 'MASTER'),
	(1011, 'Nanotechnology in Medicine', 'Physics', 'DOCTOR'),
	(1012, 'Conservation Biology and Wildlife Management', 'Biology', 'MASTER'),
	(1013, 'Synthetic Organic Chemistry', 'Chemistry', 'ENGINEER'),
	(1014, 'Aerodynamics of Supersonic Flight', 'Engineering', 'DOCTOR'),
	(1015, 'Economic Policies in Developing Countries', 'Economics', 'BACHELOR'),
	(1016, 'Cognitive Psychology and Memory', 'Psychology', 'MASTER'),
	(1017, 'Blockchain Technology and Cryptocurrency', 'Computer Science', 'BACHELOR'),
	(1018, 'Prime Number Distribution', 'Mathematics', 'MASTER'),
	(1019, 'Astrophysics: Black Holes and Neutron Stars', 'Physics', 'DOCTOR'),
	(1020, 'Marine Biology: Coral Reef Conservation', 'Biology', 'MASTER'),
	(1021, 'Organic Synthesis of Drug Compounds', 'Chemistry', 'ENGINEER'),
	(1022, 'Aeronautical Engineering: UAV Design', 'Engineering', 'DOCTOR'),
	(1023, 'Global Economic Trends', 'Economics', 'BACHELOR'),
	(1024, 'Positive Psychology and Well-Being', 'Psychology', 'MASTER'),
    (1025, 'Animals in their Tundra cycle of life', 'Biology', 'MASTER');

-- Sample Student records
INSERT INTO Student (student_name, student_surname, thesis_id, promoter_id)
VALUES
    ('Emma', 'Johnson', 1009, 101),
    ('Liam', 'Brown', 1010, 102),
    ('Olivia', 'Smith', 1003, 103),
    ('Noah', 'Lee', 1012, 104),
    ('Ava', 'Williams', 1001, 105),
    ('Sophia', 'Garcia', 1014, 106),
    ('Jackson', 'Clark', 1007, 107),
	('Lilly', 'Wells', null, 103),
    ('Isabella', 'Davis', 1008, 108),
    ('William', 'Martin', 1017, 101),
    ('Sophie', 'Anderson', 1018, 102),
    ('Mia', 'Jones', 1019, 103),
    ('James', 'Harris', 1004, 104),
    ('Oliver', 'Brown', 1013, 105),
    ('Ella', 'Wilson', 1022, 106),
    ('Benjamin', 'Thompson', 1002, 107),
    ('Amelia', 'Carter', 1024, 108),
    ('Lucas', 'White', 1023, 101),
    ('Charlotte', 'Taylor', 1021, 102),
    ('Henry', 'Brown', 1011, 103),
    ('Mila', 'King', 1020, 104),
    ('Liam', 'Parker', 1005, 105),
    ('Ella', 'Smith', 1006, 106),
    ('Ethan', 'Adams', 1015, 107),
    ('Harper', 'Wright', 1016, 108),
    ('Hillary', 'James', 1025, null),
    ('Jessie', 'Owl', null, 108);


	