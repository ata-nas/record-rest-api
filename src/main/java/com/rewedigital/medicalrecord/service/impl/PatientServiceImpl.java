package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.NoSuchPatientEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PercentNotInsuredPatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.model.mapper.PatientMapper;
import com.rewedigital.medicalrecord.repository.PatientRepository;
import com.rewedigital.medicalrecord.service.PatientService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional // TODO check which to use, this or spring package one!
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientEntity getPatientByUic(String uic) {
        return patientRepository.findByUic(uic)
                .orElseThrow(() -> new NoSuchPatientEntityFoundException("uic", uic));
    }

    @Override
    public PatientDTO getPatientByUicToDTO(String uic) {
        return patientMapper.toDTO(getPatientByUic(uic));
    }

    @Override
    public List<PatientEntity> getAllPatients() {
        List<PatientEntity> all = patientRepository.findAll();
        if (all.isEmpty()) {
            throw new NoSuchPatientEntityFoundException("No Patients found!");
        }
        return all;
    }


    @Override
    public PatientDTO createPatient(CreatePatientDTO createPatientDTO) {
        return patientMapper.toDTO(
                patientRepository.save(
                        patientMapper.toEntity(createPatientDTO)
                )
        );
    }

    @Override
    public PatientDTO updatePatient(String uic, UpdatePatientDTO updatePatientDTO) {
        return patientMapper.toDTO(
                patientRepository.save(patientMapper.toEntity(updatePatientDTO, getPatientByUic(uic)))
        );
    }

    @Override
    public void deletePatientByUic(String uic) {
        patientRepository.delete(getPatientByUic(uic));
    }

    @Override
    public List<PatientDTO> getAllPatientsToDTO() {
        return patientMapper.allToDTO(getAllPatients());
    }

//    public Integer countDistinctByInsuredFalse() {
//    @Override
//    @Override
//    public List<PatientEntity> getAllPatientsInsuredFalse() {
//        List<PatientEntity> all = patientRepository.findAllByInsuredFalse();
//        if (all.isEmpty()) {
//            throw new NoSuchPatientEntityFoundException("No Patients found!");
//        }
//        return all;
//    }
//
//    @Override
//    public List<PatientDTO> getAllPatientsInsuredFalseToDTO() {
//        return patientMapper.allToDTO(getAllPatientsInsuredFalse());
//    }
//
//    @Override
//    public List<PatientEntity> getAllPatientsInsuredTrue() {
//        List<PatientEntity> all = patientRepository.findAllByInsuredTrue();
//        if (all.isEmpty()) {
//            throw new NoSuchPatientEntityFoundException("No Patients found!");
//        }
//        return all;
//    }
//
//    @Override
//    public List<PatientDTO> getAllPatientsInsuredTrueToDTO() {
//        return patientMapper.allToDTO(getAllPatientsInsuredTrue());
//    }
//        return patientRepository.countDistinctByInsuredFalse();
//    }
//
//    @Override
//    public Integer countDistinctByInsuredTrue() {
//        return patientRepository.countDistinctByInsuredTrue();
//    }
//
//    @Override
//    public PercentNotInsuredPatientDTO totalPercentNotInsuredPatients() {
//        return new PercentNotInsuredPatientDTO().setPercentNotInsured(calculatePercentageNotInsured());
//    }
//
//    private BigDecimal calculatePercentageNotInsured() {
//        int totalPeople = getAllPatients().size();
//        int targetPeople = countDistinctByInsuredFalse();
//        return BigDecimal.valueOf(targetPeople)
//                .multiply(BigDecimal.valueOf(100.00)
//                        .setScale(4, RoundingMode.HALF_EVEN)
//                        .divide(BigDecimal.valueOf(totalPeople), RoundingMode.HALF_EVEN));
//    }
//
//    @Override
//    public PercentNotInsuredPatientDTO totalPercentInsuredPatients() {
//        return new PercentNotInsuredPatientDTO().setPercentNotInsured(calculatePercentageInsured());
//    }
//
//    private BigDecimal calculatePercentageInsured() {
//        int totalPeople = getAllPatients().size();
//        int targetPeople = countDistinctByInsuredTrue();
//        return BigDecimal.valueOf(targetPeople)
//                .multiply(BigDecimal.valueOf(100.00)
//                        .setScale(4, RoundingMode.HALF_EVEN)
//                        .divide(BigDecimal.valueOf(totalPeople), RoundingMode.HALF_EVEN));
//    }

}
