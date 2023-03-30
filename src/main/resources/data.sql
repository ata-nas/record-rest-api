-- healthcare agency
INSERT INTO pricing_hisotry (id, issue_no, appointment_fees, from_date)
VALUES (1, '1', 10.00, '2020-01-01');

-- patients
INSERT INTO patients (id, name, uic, gp_id, deleted)
VALUES (1, 'Petar', '000001', null, false),
       (2, 'Georgi', '000002', null, false),
       (3, 'Dimitar', '000003', null, false),
       (4, 'Stefan', '000004', null, false),
       (5, 'Ivan', '000005', null, false);

-- insurance history
INSERT INTO insurances(id, start_date, end_date)
VALUES (1, '2020-01-02 00:00:00', '2023-01-01 00:00:00');

INSERT INTO patients_insurances(patient_id, insurance_id)
VALUES (1, 1);

-- doctors
INSERT INTO doctors (id, birth_date, name, uic, deleted)
VALUES (1, '1974-01-01', 'Georgiev', '0001', false),
       (2, '1973-01-01', 'Petrov', '0002', false),
       (3, '1990-01-01', 'Dimitrov', '0003', false),
       (4, '1991-01-01', 'Ivanov', '0004', false),
       (5, '1984-01-01', 'Yordanov', '0005', false);

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
VALUES (1, '2022-01-01', 'Generic description', '2301010001', 1, 1, 1),
       (2, '2022-01-04', 'Generic description', '2301040001', 3, 2, 1),
       (3, '2022-01-03', 'Generic description', '2301030001', 5, 4, 1),
       (4, '2022-01-02', 'Generic description', '2301020001', 2, 3, 1),
       (5, '2022-01-07', 'Generic description', '2301070001', 4, 5, 1);

INSERT INTO appointments_diagnoses(appointment_id, diagnose_id)
VALUES (1, 1),
       (2, 2),
       (2, 3),
       (3, 4),
       (4, 4),
       (4, 3),
       (5, 1);
