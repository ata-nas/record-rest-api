package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.PatientIncomeDTO;
import com.rewedigital.medicalrecord.model.dto.stats.PatientVisitDTO;
import com.rewedigital.medicalrecord.model.dto.stats.PercentageInsuredPatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;

import java.util.List;
import java.util.Set;

public interface PatientService {

    PatientEntity getPatientByUic(String uic);

    PatientDTO getPatientByUicToDTO(String uic);

    Set<PatientEntity> getAllPatients();

    Set<PatientDTO> getAllPatientsToDTO();

    PatientDTO createPatient(CreatePatientDTO createPatientDTO);

    PatientDTO updatePatient(String uic, UpdatePatientDTO updatePatientDTO);

    void deletePatientByUic(String uic);

    Set<PatientDTO> getAllPatientsCurrentlyInsured();

    Set<PatientDTO> getAllPatientsCurrentlyNotInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyInsured();

    PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured();

    PatientVisitDTO getPatientVisitCount(String uic);

    PatientIncomeDTO getPatientsIncomeFromInsured();

    PatientIncomeDTO getPatientsIncomeFromNotInsured();

}
