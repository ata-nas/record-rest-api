package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PercentageInsuredPatientDTO;

import java.util.List;

public interface StatsService {

    List<PatientDTO> getAllPatientsCurrentlyInsured();

    List<PatientDTO> getAllPatientsCurrentlyNotInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured();

}
