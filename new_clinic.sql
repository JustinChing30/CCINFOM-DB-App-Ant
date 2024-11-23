CREATE TABLE patients (
	patient_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    sex ENUM('M','F'),
    age INT DEFAULT 0,
    contact_num VARCHAR(15),
    email_address VARCHAR(40),
    address VARCHAR(80),
    PRIMARY KEY (patient_id)
	);
INSERT INTO
patients (first_name, last_name, sex, age, contact_num, email_address, address)
VALUES
('Burnice', 'White', 'F', 18, "09217890123", 'burnice_white@gmail.com', '1919 Recto Ave., Sampaloc, Manila, Metro Manila'),
('Juan', 'Dela Cruz', 'M', 17, "09156789012", 'juan_delacruz@gmail.com', '808 Taft Avenue, Malate, Manila, Metro Manila'),
('Michael', 'Morales', 'M', 17, "09154321888", 'michael_morales@gmail.com', '909 Legaspi St., Legazpi City, Albay'), 
('Carlos', 'Manalo', 'M', 18, "0921905434", 'carlos_manalo@gmail.com', '2525 A. Bonifacio Ave., Cainta, Rizal'), 
('Daniel', 'Santiago', 'M', 20, "09214267075", 'daniel_santiago@gmail.com', '456 Lopez Jaena St., Cebu City, Cebu'), 
('Ethan', 'Kim', 'M', 19, "09214643421", 'ethan_kim@gmail.com', '2020 Dacudao Ave., Davao City, Davao del Sur'), 
('Nicole', 'Morales', 'F', 17, "09213095691", 'nicole_morales@gmail.com', '2121 Real St., Dumaguete City, Negros Oriental'), 
('Antonio', 'Lacerna', 'M', 19, "09157097057", 'antonio_lacerna@gmail.com', '2222 Del Pilar St., Malolos City, Bulacan'), 
('Venice', 'Ozaeta', 'F', 19, "09219025641", 'venice_ozaeta@gmail.com', '1111 J.P. Rizal St., Calamba, Laguna'), 
('Sophia', 'Atienza', 'F', 21, "09153420956", 'sophia_atienza@gmail.com', '1717 E. Rodriguez Sr. Ave., Quezon City, Metro Manila'); 



CREATE TABLE inventory (
    item_id INT NOT NULL AUTO_INCREMENT,
    item_name VARCHAR(50) NOT NULL,
    stock INT NOT NULL,
    last_stocked DATE NOT NULL,
    PRIMARY KEY (item_id)
);
INSERT INTO
inventory (item_name, stock, last_stocked)
VALUES
('Urine Cup', 100, '2024-11-23'),
('Needle', 100, '2024-11-23'),
('Syringe', 100, '2024-11-23'),
('X-ray Film', 100, '2024-11-23'),
('Antiseptic Wipes', 100, '2024-11-23'),
('Medical Gloves', 100, '2024-11-23');



CREATE TABLE staff (
	staff_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    specialization VARCHAR(30) NOT NULL,
    hire_date DATE NOT NULL,
    PRIMARY KEY (staff_id)
    );
INSERT INTO
staff (first_name, last_name, specialization, hire_date)
VALUES
('Albert','Dalit','doctor','2022-04-14'),
('Miko','Santos','dentist','2023-07-29'),
('Mikaela','Reeve','doctor','2022-02-07'),
('Danika','Uy','dentist','2021-08-08'),
('Francis','Dy','nurse','2023-06-15'),
('Ryan','Villacorte','nurse','2023-06-15'),
('Christian','Demafelix','assistant','2024-07-01'),
('Miah','Sarzate','nurse','2023-06-15'),
('Hannah','Palcuto','assistant','2024-03-01'),
('Cheska','Padual','assistant','2024-03-02');
    


CREATE TABLE team_record (
	team_id INT NOT NULL AUTO_INCREMENT,
    doctor INT NULL, 
    dentist INT NULL,
    nurse INT NULL, 
    assistant INT NULL,
    PRIMARY KEY (team_id),

    
    FOREIGN KEY (doctor) REFERENCES staff(staff_id) ON DELETE SET NULL,
    FOREIGN KEY (dentist) REFERENCES staff(staff_id) ON DELETE SET NULL,
    FOREIGN KEY (nurse) REFERENCES staff(staff_id) ON DELETE SET NULL,
    FOREIGN KEY (assistant) REFERENCES staff(staff_id) ON DELETE SET NULL
    );
INSERT INTO 
team_record (doctor, dentist, nurse, assistant)
VALUES
(1,2,5, 7),
(1,4,NULL,10),
(3,4,8, 9),
(1,4,6, 7),
(3,NULL,NULL, NULL),
(3,4,6, 7),
(1,NULL,5, NULL),
(NULL,NULL,NULL,10),
(1,NULL,NULL, NULL),
(3,NULL,NULL, NULL);



CREATE TABLE appointments (
	appointment_id INT NOT NULL AUTO_INCREMENT,
    patient_id INT,
    team_id INT,
    appointment_time DATE,
    PRIMARY KEY (appointment_id),
    
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES team_record(team_id) ON DELETE CASCADE
	);
INSERT INTO
appointments (patient_id, team_id, appointment_time)
VALUES
(1, 1,   '2024-11-29'),
(2, 2,   '2024-10-07'),
(3, 3,   '2024-07-29'),
(4, 4,   '2024-07-25'),
(5, 5,   '2024-08-02'),
(6, 6,   '2024-08-02'),
(7, 7,   '2024-08-02'),
(8, 8,   '2024-08-02'),
(9, 9,   '2024-11-01'),
(10, 10, '2024-11-04');




CREATE TABLE health_record (
	record_id INT NOT NULL AUTO_INCREMENT,
    schedule_id INT,
    dental ENUM('yes','no') NOT NULL,
    blood_test ENUM('yes','no') NOT NULL,
    urinalysis ENUM('yes','no') NOT NULL,
    abdominal_Xray ENUM('yes','no') NOT NULL,
    test_conduct INT,
    total_fees DECIMAL(8,2),
    PRIMARY KEY (record_id),
    
    FOREIGN KEY (schedule_id) REFERENCES appointments(appointment_id) ON DELETE CASCADE
    );
INSERT INTO 
health_record (schedule_id, dental, blood_test, urinalysis,
				abdominal_Xray, test_conduct, total_fees)
VALUES    
( 1, 'yes', 'yes', 'yes', 'yes', 4, 1050.00),
( 2, 'yes', 'yes', 'no',  'yes', 3,  950.00),
( 3, 'yes', 'yes', 'yes', 'no',  3,  750.00),
( 4, 'yes', 'no',  'yes', 'no',  2,  600.00),
( 5, 'no',  'no',  'no',  'yes', 1,  300.00),
( 6, 'yes', 'yes', 'yes', 'yes', 4, 1050.00),
( 7, 'no',  'no',  'yes', 'no',  1,  100.00),
( 8, 'no',  'yes', 'no',  'no',  1,  150.00),
( 9, 'no',  'no',  'no',  'yes', 1,  300.00),
(10, 'no',  'no',  'no',  'yes', 1,  300.00);

CREATE TABLE health_record_items (
    record_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity_used INT NOT NULL,
    PRIMARY KEY (record_id, item_id),
    
    FOREIGN KEY (record_id) REFERENCES health_record(record_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES inventory(item_id) ON DELETE CASCADE
);
INSERT INTO
health_record_items (record_id, item_id, quantity_used)
VALUES
(1, 1, 1), (1, 2, 1), (1, 3, 1), (1, 4, 1), (1, 5, 1), (1, 6, 2),
(2, 2, 1), (2, 3, 1), (2, 4, 1), (2, 5, 1), (2, 6, 2),
(3, 1, 1), (3, 2, 1), (3, 3, 1), (3, 5, 1), (3, 6, 2),
(4, 1, 1), (4, 6, 1),
(5, 4, 1),
(6, 1, 1), (6, 2, 1), (6, 3, 1), (6, 4, 1), (6, 5, 1), (6, 6, 2),
(7, 1, 1),
(8, 2, 1), (8, 3, 1), (8, 5, 1), (8, 6, 1), 
(9, 4, 1),
(10, 4, 1);
