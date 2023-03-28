package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;

import java.util.List;

public interface PatientService {

    PatientEntity getPatientByUic(String uic);

    PatientDTO getPatientByUicToDTO(String uic);

    List<PatientEntity> getAllPatients();

    List<PatientDTO> getAllPatientsToDTO();

//    List<PatientEntity> getAllPatientsInsuredFalse();
//
//    List<PatientDTO> getAllPatientsInsuredFalseToDTO();
//
//    List<PatientEntity> getAllPatientsInsuredTrue();
//
//    List<PatientDTO> getAllPatientsInsuredTrueToDTO();

    PatientDTO createPatient(CreatePatientDTO createPatientDTO);

    PatientDTO updatePatient(String uic, UpdatePatientDTO updatePatientDTO);

    void deletePatientByUic(String uic);

//    Integer countDistinctByInsuredFalse();
//
//    Integer countDistinctByInsuredTrue();
//
//    PercentNotInsuredPatientDTO totalPercentNotInsuredPatients();
//
//    PercentNotInsuredPatientDTO totalPercentInsuredPatients();

}
