package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.diagnose.NoSuchDiagnoseEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import com.rewedigital.medicalrecord.model.mapper.diagnose.DiagnoseMapper;
import com.rewedigital.medicalrecord.repository.DiagnoseRepository;
import com.rewedigital.medicalrecord.service.DiagnoseService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnoseServiceImpl implements DiagnoseService {

    private final DiagnoseRepository diagnoseRepository;
    private final DiagnoseMapper diagnoseMapper;

    @Override
    public DiagnoseEntity getById(Long id) {
        return diagnoseRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchDiagnoseEntityFoundException(
                                "Diagnose with ID: '" + id + "' not found!"
                        )
                );
    }

    @Override
    public DiagnoseDTO getByIdToDTO(Long id) {
        return diagnoseMapper.diagnoseEntityToDiagnoseDTO(getById(id));
    }

    @Override
    public List<DiagnoseEntity> getAllDiagnoses() {
        List<DiagnoseEntity> diagnoses = diagnoseRepository.findAll();
        if (diagnoses.isEmpty()) {
            throw new NoSuchDiagnoseEntityFoundException("No Diagnoses found!");
        }
        return diagnoses;
    }

    @Override
    public List<DiagnoseDTO> getAllDiagnosesToDTO() {
        return diagnoseMapper.allDiagnoseEntityToDiagnoseDTO(getAllDiagnoses());
    }

    @Override
    public DiagnoseDTO createDiagnose(CreateDiagnoseDTO diagnoseDTO) {
        return diagnoseMapper.diagnoseEntityToDiagnoseDTO(
                diagnoseRepository.save(diagnoseMapper.createDiagnoseDTOToDiagnoseEntity(diagnoseDTO))
        );
    }

    @Override
    public void deleteDiagnoseById(Long id) {
        diagnoseRepository.delete(getById(id));
    }

}
