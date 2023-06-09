package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.*;
import com.rewedigital.medicalrecord.service.*;

import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseVisitDTO;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This @Service acts as a binding or orchestration of all other relevant or needed services
 * to have all the querying functionality needed for the task so that the @RestController StatsController
 * can interact only with this class. The actual querying and logic is delegated to the respective @Service.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final DiagnoseService diagnoseService;

    @Override
    public Set<PatientDTO> getAllPatientsCurrentlyInsured() {
        return patientService.getAllPatientsCurrentlyInsured();
    }

    @Override
    public Set<PatientDTO> getAllPatientsCurrentlyNotInsured() {
        return patientService.getAllPatientsCurrentlyNotInsured();
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyInsured() {
        return patientService.getPercentageCurrentlyInsured();
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured() {
        return patientService.getPercentageCurrentlyNotInsured();
    }

    @Override
    public CountDoctorIncomeHigherThanDTO countDoctorsWithHigherIncomeThanGiven(long income) {
        return doctorService.countDoctorsWithHigherIncomeThanGiven(income);
    }

    @Override
    public TotalIncomeDTO getTotalIncome() {
        return appointmentService.getTotalIncome();
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUic(String uic) {
        return doctorService.getDoctorIncomeByUic(uic);
    }

    @Override
    public PatientVisitDTO getPatientVisitCount(String uic) {
        return patientService.getPatientVisitCount(uic);
    }

    @Override
    public DiagnoseVisitDTO getDiagnoseVisitCount(String name) {
        return diagnoseService.getDiagnoseVisitCount(name);
    }

    @Override
    public DiagnoseIncomeDTO getDiagnoseIncomeByName(String name) {
        return diagnoseService.getDiagnoseIncomeByName(name);
    }

    @Override
    public PatientIncomeDTO getPatientsIncomeFromInsured() {
        return patientService.getPatientsIncomeFromInsured();
    }

    @Override
    public PatientIncomeDTO getPatientsIncomeFromNotInsured() {
        return patientService.getPatientsIncomeFromNotInsured();
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUicFromInsuredPatients(String uic) {
        return doctorService.getDoctorIncomeByUicFromInsuredPatients(uic);
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUicFromNotInsuredPatients(String uic) {
        return doctorService.getDoctorIncomeByUicFromNotInsuredPatients(uic);
    }

}
