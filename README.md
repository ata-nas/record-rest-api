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

1. DONE - List of all the patients, who have health insurance
2. DONE - The percentage of patients who have no insurance (in accordance of the total number of patients)
3. DONE - The total income of all the visits of all patients to all doctors
4. DONE - The income from visits fees of a particular doctor
5. DONE - The number of visits of a given patient
6. DONE - The number of visits by a diagnosis
7. DONE - The number of doctors who have income greater than a given one
8. DONE - The total income of visits, by a given diagnosis
9. DONE - The total income of patients who have no health insurance
10. DONE - The income of a concrete doctor of patients who have health insurance

## IMPORTANT:

-   I have made the project as extensive as possible with the knowledge I currently posess. I know it has some flaws,
    I know where they are and how to fix them, but the time was low to do everything unfortunately.

-   I have tried to use clean code to the best of my abilities, splitting methods to do single things, not using any loops etc.,
    delegating clearly the responsibilities towards the respective services, uniformity in my approach towards everything, using
    inheritance and polymorphism, making things easliy expandable - which really saved me, since I forgot to implement soft-delete
    in the beginning, now I deeply appreciate the SOLID principles, seeing how easy was for me to update my project with new functionaltiy :).

-   In general, I have learned a LOT from this project, until reaching the conclusive form of the API, I went through many
    things and different approaches, I researched a lot and used all best practices I know of and that I have acquired.
    I have tried to understand (and I did) everything I am doing, what is happening in the background and how it
    functions (of course for the most part and from more broader view). I fixed a lot of issues and changed my
    approach for many thing many times in these two weeks, my inital idea is vastly different from the end result.

-   Error handling is global, managed by `AppExceptionHandler` abstraction. I throw exceptions in the service directly. They are intercepted and managed.
    Also, there is a fallback in case an unanticipated error occurs, then to the REST client is returned status 500 internal server error, so not to expose internal details.

-   Validations are done through the binding result using jakarta validations and custom validations. Everytime there is not a valid state,
    it gets returned as error dto through my `AppExceptionHandler` that intercepts binding errors.

-   To make things with Exceptions cleaner, I have implemented a hierarchical structure with inheritance, for easier management
    in the `AppExceptionHandler`. The returning dto is constructed dynamically inside the exception entity.

-   I have made my custom validations to be able to have access to the repository to check if entity exists or others.
    This design decision is motivated for the reasons that, if the outside world hands me incorrect data, I want to stop it immediately
    and I use the repo directly and not the base service, because repo methods are more static and not prone to change.
    The reason I do this operation is, although it might seem taxing, for such small project we cannot see the benefits,
    but if the data needs to travel through a lot of places and will activate a lot of processes (mind you with the risk of security since it will be incorrect if not checked that way)
    and that in turn can be infinitely more taxing than 1 query beforehand. Not to mention again the security benefits...

-   MapStruct is used very extensively, it has direct access to repositories in order to automate mapping process. I restrain
    from using services there that technically have error handling in them, because services are prone to change, while I can
    count on the methods in the repo to be static, and moreover, I handle null values differently sometimes, like in creating new patient,
    I will allow null to be passed on gp uic, because is optional. If however not null is passed, then I go to repo and throw exception
    if not found. This is very different from the service error handling where null is not allowed. Services are responsible to find
    and get desired entity in PUT request for updating it, but the mapper is responsible to correctly map it, because it has access
    to repositories, other than the service has, this is done so that the particular service has access and is responsible only to
    do CRUD with one particular entity, the entity the service is for. In this way I think I separate concerns well, also
    I automate the mapping process, and it is being serviced from one place only!

-   I have made the decision to combine the base service of `DoctorEntity` and `GpEntity` since they are basically the same thing,
    `GpEntity` exists only with `@MapsId` to note if a given doctor is a GP or not. This happens with the very simple check, going to the
    `GpRepository` and seeing if a particular id is there. Creating and Updating are sometimes interchangeable. I do think they belong
    in the same service that has access to both `DoctrorRepository` and `GpRepository` to use freely. Moreover, `GpEntity` is a
    descendent of `DoctorEntity`.

## Thoughts

-   Making CRUD not based on `id` but `@NaturalId` because is more consistent, eventhoug `id` is indexed in db and is faster.

-   Decided to not make update functionality on `SpecialtyEntity` and `DiagnoseEntity`, do not see the point here as these
    things are static in nature and would probably not be used by end user (not have the right to update freely). If mistake is made, we can delete and create again.
