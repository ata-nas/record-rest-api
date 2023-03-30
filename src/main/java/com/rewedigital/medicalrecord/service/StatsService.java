package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.PercentageInsuredPatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.CountDoctorIncomeHigherThanDTO;
import com.rewedigital.medicalrecord.model.dto.stats.TotalIncomeDTO;

import java.util.List;

public interface StatsService {

    List<PatientDTO> getAllPatientsCurrentlyInsured();

    List<PatientDTO> getAllPatientsCurrentlyNotInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured();

    CountDoctorIncomeHigherThanDTO countDoctorsWithHigherIncomeThanGiven(long income);

    TotalIncomeDTO getTotalIncome();
}
