-- healthcare agency
INSERT INTO healthcare_agencies (id, country,  appointment_fees)
VALUES (1, 'BULGARIA', 100.00);

-- patients
INSERT INTO patients (id, insured, name, uic, gp_id)
VALUES (1, true, 'Petar', '000001', null),
       (2, true, 'Georgi', '000002', null),
       (3, false, 'Dimitar', '000003', null),
       (4, false, 'Stefan', '000004', null),
       (5, true, 'Ivan', '000005', null);

-- doctors
INSERT INTO doctors (id, birth_date, name, uic)
VALUES (1, '1974-01-01', 'Georgiev', '0001'),
       (2, '1973-01-01', 'Petrov', '0002'),
       (3, '1990-01-01', 'Dimitrov', '0003'),
       (4, '1991-01-01', 'Ivanov', '0004'),
       (5, '1984-01-01', 'Yordanov', '0005');

-- gp
INSERT INTO gps(id)
VALUES (1),
       (3);

-- specialties
INSERT INTO specialties(id, name)
VALUES (1, 'PEDIATRICS'),
       (2, 'SURGERY'),
       (3, 'CARDIOLOGY');

-- doctors_specialties
INSERT INTO doctors_specialty(doctor_entity_id, specialty_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (3, 3),
       (4, 1),
       (5, 2);

INSERT INTO diagnoses(id, name)
VALUES (1, 'HEALTHY'),
       (2, 'FLU'),
       (3, 'PNEUMONIA'),
       (4, 'COLD');
