package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchPatientEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PercentageInsuredPatientDTO;
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
import java.time.LocalDate;
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
    public List<PatientDTO> getAllPatientsToDTO() {
        return patientMapper.allToDTO(getAllPatients());
    }

    @Override
    public PatientDTO createPatient(CreatePatientDTO createPatientDTO) {
        if (patientRepository.findByUic(createPatientDTO.getUic()).isPresent()) {
            patientRepository.softCreate(createPatientDTO.getUic());
            return updatePatient(createPatientDTO.getUic(), patientMapper.toDTO(createPatientDTO));
        }
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
        patientRepository.softDelete(uic);
    }

    @Override
    public List<PatientDTO> getAllPatientsCurrentlyInsured() {
        List<PatientDTO> all = patientMapper.allToDTO(patientRepository.findAllCurrentlyInsured(LocalDate.now()));
        if (all.isEmpty()) {
            throw new NoSuchPatientEntityFoundException("No Patients currently insured found!");
        }
        return all;
    }

    @Override
    public List<PatientDTO> getAllPatientsCurrentlyNotInsured() {
        List<PatientDTO> all = patientMapper.allToDTO(patientRepository.findAllCurrentlyNotInsured(LocalDate.now()));
        if (all.isEmpty()) {
            throw new NoSuchPatientEntityFoundException("No Patients currently not insured found!");
        }
        return all;
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyInsured() {
        return new PercentageInsuredPatientDTO().setPercentage(calculatePercentInsured());
    }

    private BigDecimal calculatePercentInsured() {
        long all = patientRepository.count();
        long insured = patientRepository.countAllCurrentlyInsured(LocalDate.now());
        return BigDecimal.valueOf(insured)
                .multiply(BigDecimal.valueOf(100.00))
                .setScale(4, RoundingMode.HALF_EVEN)
                .divide(BigDecimal.valueOf(all), RoundingMode.HALF_EVEN);
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured() {
        return new PercentageInsuredPatientDTO().setPercentage(calculatePercentNotInsured());
    }

    private BigDecimal calculatePercentNotInsured() {
        long all = patientRepository.count();
        long insured = patientRepository.countAllCurrentlyNotInsured(LocalDate.now());
        return BigDecimal.valueOf(insured)
                .multiply(BigDecimal.valueOf(100.00))
                .setScale(4, RoundingMode.HALF_EVEN)
                .divide(BigDecimal.valueOf(all), RoundingMode.HALF_EVEN);
    }

}
