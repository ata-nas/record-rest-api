package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.*;
import com.rewedigital.medicalrecord.model.validation.ExistingDiagnoseNameValidation;
import com.rewedigital.medicalrecord.model.validation.ExistingDoctorUicValidation;
import com.rewedigital.medicalrecord.model.validation.ExistingPatientUicValidation;
import com.rewedigital.medicalrecord.service.StatsService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  This @RestController is the one that displays all the queries from the task that was given.
 * For completeness, also are added opposite of the desired results, where logically applied
 * (i.e. if asked "list all where true", also added "list all where false").
 * </p> <br/>
 * <p>
 * Task:
 * 1. List of all the patients, who have health insurance
 * 2. The percentage of patients who have no insurance (in accordance of the total number of patients)
 * 3. The total income of all the visits of all patients to all doctors
 * 4. The income from visits fees of a particular doctor
 * 5. The number of visits of a given patient
 * 6. The number of visits by a diagnosis
 * 7. The number of doctors who have income greater than a given one
 * 8. The total income of visits, by a given diagnosis
 * 9. The total income of patients who have no health insurance
 * 10. The income of a concrete doctor of patients who have health insurance
 * </p>
 */
@RestController
@RequestMapping("/api/healthcare/bulgaria/stats")
@RequiredArgsConstructor
@Validated
public class StatsController {

    private final StatsService statsService;

    /**
     * 3. The total income of all the visits of all patients to all doctors
     * @return the total income of all the visits of all patients to all doctors
     */
    @GetMapping("/income")
    public ResponseEntity<TotalIncomeDTO> totalIncome() {
        return ResponseEntity.ok(statsService.getTotalIncome());
    }

    /**
     * 1. List of all the patients, who have health insurance
     * @return list of all the patients, who have health insurance
     */
    @GetMapping("/patients/insured")
    public ResponseEntity<List<PatientDTO>> patientsCurrentlyInsured() {
        return ResponseEntity.ok(statsService.getAllPatientsCurrentlyInsured());
    }

    /**
     * 1.1 List of all the patients, who have no health insurance
     * @return list of all the patients, who have no health insurance
     */
    @GetMapping("/patients/not-insured")
    public ResponseEntity<List<PatientDTO>> patientsCurrentlyNotInsured() {
        return ResponseEntity.ok(statsService.getAllPatientsCurrentlyNotInsured());
    }

    /**
     * 2.1 The percentage of patients who have insurance (in accordance of the total number of patients)
     * @return the percentage of patients who have insurance (in accordance of the total number of patients)
     */
    @GetMapping("/patients/percent/insured")
    public ResponseEntity<PercentageInsuredPatientDTO> patientsCurrentlyInsuredPercent() {
        return ResponseEntity.ok(statsService.getPercentageCurrentlyInsured());
    }

    /**
     * 2. The percentage of patients who have no insurance (in accordance of the total number of patients)
     * @return the percentage of patients who have no insurance (in accordance of the total number of patients)
     */
    @GetMapping("/patients/percent/not-insured")
    public ResponseEntity<PercentageInsuredPatientDTO> patientsCurrentlyNotInsuredPercent() {
        return ResponseEntity.ok(statsService.getPercentageCurrentlyNotInsured());
    }

    /**
     * 5. The number of visits of a given patient
     * @param uic the uic of a patient
     * @return the number of visits of a given patient
     */
    @GetMapping("/patients/visits/{uic}")
    public ResponseEntity<PatientVisitDTO> patientsCountAllVisitsOfPatientWithUic(
            @NotBlank
            @ExistingPatientUicValidation
            @PathVariable String uic
    ) {
        return ResponseEntity.ok(statsService.getPatientVisitCount(uic));
    }

    /**
     * 9.1 The total income of patients who have health insurance (at time of appointment)
     * @return the total income of patients who have no health insurance (at time of appointment)
     */
    @GetMapping("/patients/income/insured")
    public ResponseEntity<PatientIncomeDTO> patientsIncomeFromInsured() {
        return ResponseEntity.ok(statsService.getPatientsIncomeFromInsured());
    }

    /**
     * 9. The total income of patients who have no health insurance (at time of appointment)
     * @return the total income of patients who have no health insurance (at time of appointment)
     */
    @GetMapping("/patients/income/not-insured")
    public ResponseEntity<PatientIncomeDTO> patientsIncomeFromNotInsured() {
        return ResponseEntity.ok(statsService.getPatientsIncomeFromNotInsured());
    }

    /**
     * 4. The income from visits fees of a particular doctor
     * @param uic unique identifier of doctor
     * @return the income from visits fees of a particular doctor
     */
    @GetMapping("/doctors/income/{uic}")
    public ResponseEntity<DoctorIncomeDTO> doctorsIncomeOfDoctorWithUic(
            @NotNull
            @ExistingDoctorUicValidation
            @PathVariable String uic
    ) {
        return ResponseEntity.ok(statsService.getDoctorIncomeByUic(uic));
    }

    /**
     * 10. The income of a concrete doctor of patients who have health insurance
     * @param uic unique identifier of doctor
     * @return the income of a concrete doctor of patients who have health insurance
     */
    @GetMapping("/doctors/income/insured/{uic}")
    public ResponseEntity<DoctorIncomeDTO> doctorsIncomeOfDoctorWithUicFromInsuredPatients(
            @NotNull
            @ExistingDoctorUicValidation
            @PathVariable String uic
    ) {
        return ResponseEntity.ok(statsService.getDoctorIncomeByUicFromInsuredPatients(uic));
    }

    /**
     * 10.1 The income of a concrete doctor of patients who have no health insurance (at time of appointment)
     * @param uic unique identifier of doctor
     * @return the income of a concrete doctor of patients who have no health insurance (at time of appointment)
     */
    @GetMapping("/doctors/income/not-insured/{uic}")
    public ResponseEntity<DoctorIncomeDTO> doctorsIncomeOfDoctorWithUicFromNotInsuredPatients(
            @NotNull
            @ExistingDoctorUicValidation
            @PathVariable String uic
    ) {
        return ResponseEntity.ok(statsService.getDoctorIncomeByUicFromNotInsuredPatients(uic));
    }

    /**
     * 7. The number of doctors who have income greater than a given one
     * @param income filter for the count
     * @return the number of doctors who have income greater than a given one
     */
    @GetMapping("/doctors/income/above/{income}")
    public ResponseEntity<CountDoctorIncomeHigherThanDTO> doctorsCountDoctorsWithHigherIncomeThanGiven(
            @NotNull
            @PositiveOrZero
            @PathVariable Long income
    ) {
        return ResponseEntity.ok(statsService.countDoctorsWithHigherIncomeThanGiven(income));
    }

    /**
     * 6. The number of visits by a diagnosis
     * @param name the name of the diagnosis to query
     * @return the number of visits by a diagnosis
     */
    @GetMapping("/diagnoses/visits/{name}")
    public ResponseEntity<DiagnoseVisitDTO> diagnosesCountAllVisitsWithDiagnoseName(
            @NotBlank
            @ExistingDiagnoseNameValidation
            @PathVariable String name
    ) {
        return ResponseEntity.ok(statsService.getDiagnoseVisitCount(name));
    }

    /**
     * 8. The total income of visits, by a given diagnosis
     * @param name of the diagnosis to query
     * @return the total income of visits, by a given diagnosis
     */
    @GetMapping("/diagnoses/income/{name}")
    public ResponseEntity<DiagnoseIncomeDTO> diagnosesIncomeOfDiagnoseWithName(
            @NotBlank
            @ExistingDiagnoseNameValidation
            @PathVariable String name
    ) {
        return ResponseEntity.ok(statsService.getDiagnoseIncomeByName(name));
    }

}
