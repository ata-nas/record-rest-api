package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.patient.NoSuchPatientEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.model.mapper.PatientMapper;
import com.rewedigital.medicalrecord.repository.PatientRepository;
import com.rewedigital.medicalrecord.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientEntity findByUic(String uic) {
        return patientRepository.findByUic(uic)
                .orElseThrow(() -> new NoSuchPatientEntityFoundException("uic", uic));
    }

    @Override
    public PatientDTO findByUicToDTO(String uic) {
        return patientMapper.patientEntityToPatientDTO(findByUic(uic));
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
        return patientMapper.allPatientEntityToPatientDTO(getAllPatients());
    }

    @Override
    public PatientDTO createPatient(CreatePatientDTO createPatientDTO) {
        return patientMapper.patientEntityToPatientDTO(
                patientRepository.save(
                        patientMapper.createPatientDTOToPatientEntity(createPatientDTO)
                )
        );
    }

    @Override
    public PatientDTO updatePatient(PatientEntity patientEntity) {
        return patientMapper.patientEntityToPatientDTO(patientRepository.save(patientEntity));
    }


    @Override
    public void deletePatientByUic(String uic) {
        patientRepository.delete(findByUic(uic));
    }

}
