package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PercentNotInsuredPatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;

import java.util.List;

public interface PatientManagerService {

    PatientEntity findByUic(String uic);
    PatientDTO findByUicToDTO(String uic);
    List<PatientEntity> getAllPatients();
    List<PatientEntity> getAllPatientsInsuredFalse();
    List<PatientDTO> getAllPatientsInsuredFalseToDTO();
    List<PatientEntity> getAllPatientsInsuredTrue();
    List<PatientDTO> getAllPatientsInsuredTrueToDTO();
    List<PatientDTO> getAllPatientsToDTO();
    PatientDTO createPatient(CreatePatientDTO createPatientDTO);
    PatientDTO updatePatient(String uic, UpdatePatientDTO updatePatientDTO);
    void deletePatientByUic(String uic);
    PercentNotInsuredPatientDTO totalPercentNotInsuredPatients();
    PercentNotInsuredPatientDTO totalPercentInsuredPatients();
}
