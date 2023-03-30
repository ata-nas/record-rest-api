package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.*;
import com.rewedigital.medicalrecord.service.*;

import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseVisitDTO;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final DiagnoseService diagnoseService;

    @Override
    public List<PatientDTO> getAllPatientsCurrentlyInsured() {
        return patientService.getAllPatientsCurrentlyInsured();
    }

    @Override
    public List<PatientDTO> getAllPatientsCurrentlyNotInsured() {
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

}
