-- healthcare agency
INSERT INTO pricing_hisotry (id, appointment_fees, start_date, end_date)
VALUES (1, 10.00, '1990-01-01', '2023-03-01');

-- patients
INSERT INTO patients (id, name, uic, gp_id)
VALUES (1, 'Petar', '000001', null),
       (2, 'Georgi', '000002', null),
       (3, 'Dimitar', '000003', null),
       (4, 'Stefan', '000004', null),
       (5, 'Ivan', '000005', null);

-- insurance history
INSERT INTO insurances(id, end_date, start_date)
VALUES (1, '2023-01-02 00:00:00', '2023-01-01 00:00:00');

INSERT INTO patients_insurances(patient_id, insurance_id)
VALUES (1, 1);

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
INSERT INTO specialties(id, name, deleted)
VALUES (1, 'PEDIATRICS', false),
       (2, 'SURGERY', false),
       (3, 'CARDIOLOGY', false);

-- doctors_specialties
INSERT INTO doctors_specialties(doctor_id, specialty_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (3, 3),
       (4, 1),
       (5, 2);

-- diagnoses
INSERT INTO diagnoses(id, name, deleted)
VALUES (1, 'HEALTHY', false),
       (2, 'FLU', false),
       (3, 'PNEUMONIA', false),
       (4, 'COLD', false);


INSERT INTO appointments(id, date, description, uic, doctor_id, patient_id, price_history_id)
VALUES (1, '2023-01-01', 'Generic description', '2301010001', 1, 1, 1),
       (2, '2023-01-04', 'Generic description', '2301040001', 3, 2, 1),
       (3, '2023-01-03', 'Generic description', '2301030001', 5, 4, 1),
       (4, '2023-01-02', 'Generic description', '2301020001', 2, 3, 1),
       (5, '2023-01-07', 'Generic description', '2301070001', 4, 5, 1);

INSERT INTO appointments_diagnoses(appointment_id, diagnose_id)
VALUES (1, 1),
       (2, 2),
       (2, 3),
       (3, 4),
       (4, 4),
       (4, 3),
       (5, 1);