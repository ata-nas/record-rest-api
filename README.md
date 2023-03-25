# README for MedicalRecord by Atanas Atanasov

## Task:

Create REST API to handle the processes that concern patients' visits to doctors
and keep track of the history of diseases.

The doctor has a name, birthdate, a Specialty (Pediatrics, Surgery, Cardiologist),
and can be a general practitioner.

The patient has a name and pays for health insurance.

The patient has to choose GP (one of the doctors who is a GP) and a patient can be examined by a doctor.
The doctor has to give diagnosis the patient.

When the patient visits the doctor there is a fee. If the patient has health insurance,
he/she doesn't have to pay the fee otherwise, the patient has to pay the fee.
This fee is the same for all patients and all doctors.

Implement the CRUD operations of the patients, doctors, and visits, plus the following functionalities:

1. List of all the patients, who have health insurance 
2. The percentage of patients who have no insurance (in accordance of the total number of patients)
3. The total income of all the visits of all patients to all doctors 
4. The income from visits fees of a particular doctor 
5. The number of visits of a given patient 
6. The number of visits by a diagnosis 
7. The number of doctors who have income greater than a given one 
8. The total income of visits, by a given diagnosis 
9. The total income of patients who have no health insurance 
10. The income of a concrete doctor of patients who have health insurance

## IMPORTANT:

- Since we look at the API in the way that the healthcare system is a single instance, I have started to construct
all parts of it modularly, since I am always expected to be in the domain of this single healthcare system. If that
is not the case, then the `HealthcareAgencyEntity` can house a lot of info for the particular agency and to have
connections with doctors, patients etc. and to make a `HealthcareAgencyManagerService` so that everything will be
done through there, and the uri would be `/api/healthcare/{agency}/...` and I would manage all other entities based on that,
because I will have one massive `HealthcareAgencyEntity` that gives me access to all it's related entities
and a `HealthcareAgencyManagerService` that orchestrates individual smaller services if needed.
But honestly in real life I do not think that is possible for obvious legal
and many more reasons (if looked through the POV of healthcare agencies of different countries, maybe if there are
many agencies in the same country it would make sense, but I still think it won't be done that way),
so this `HealthcareAgencyEntity` acts only as a "details" table that describes all the commonalities within the system
(i.e. fees in this case, but can of course house many more). In my case, I simply use this table for the fees, since is
in the Task. PLEASE NOTE: I have made the root uri to be "/api/healthcare/bulgaria/..." it is not dynamic since as I
explain, we are in the domain of a single healthcare agency!

## Thoughts

- Making CRUD not based on `id` but `@NaturalId` because is more consistent.
- Decided to not make update functionality on `SpecialtyEntity` and `DiagnoseEntity`, do not see the point here as these
things are static in nature and would probably not be used by end user. If mistake is made, we can delete and create again.
- HealthcareAgency is initialized with the data.sql script to be id - 1 and fee 100.00, country - 'BULGARIA', there is no
functionality to add or delete this , since we consider that this entity is only one and will always exist to keep
info for our domain.