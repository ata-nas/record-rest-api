package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.*;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseVisitDTO;

import java.util.List;

public interface StatsService {

    List<PatientDTO> getAllPatientsCurrentlyInsured();

    List<PatientDTO> getAllPatientsCurrentlyNotInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured();

    CountDoctorIncomeHigherThanDTO countDoctorsWithHigherIncomeThanGiven(long income);

    TotalIncomeDTO getTotalIncome();

    DoctorIncomeDTO getDoctorIncomeByUic(String uic);

    PatientVisitDTO getPatientVisitCount(String uic);

    DiagnoseVisitDTO getDiagnoseVisitCount(String name);

    DiagnoseIncomeDTO getDiagnoseIncomeByName(String name);

    PatientIncomeDTO getPatientsIncomeFromInsured();

    PatientIncomeDTO getPatientsIncomeFromNotInsured();

    DoctorIncomeDTO getDoctorIncomeByUicFromInsuredPatients(String uic);

    DoctorIncomeDTO getDoctorIncomeByUicFromNotInsuredPatients(String uic);

}
