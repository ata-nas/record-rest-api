package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchDiagnoseEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseIncomeDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import com.rewedigital.medicalrecord.model.mapper.DiagnoseMapper;
import com.rewedigital.medicalrecord.repository.DiagnoseRepository;
import com.rewedigital.medicalrecord.service.DiagnoseService;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseVisitDTO;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class DiagnoseServiceImpl implements DiagnoseService {

    private final DiagnoseRepository diagnoseRepository;
    private final DiagnoseMapper diagnoseMapper;

    @Override
    public DiagnoseEntity getByName(String name) {
        return diagnoseRepository.findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new NoSuchDiagnoseEntityFoundException("name", name));
    }

    @Override
    public DiagnoseDTO getByNameToDTO(String name) {
        return diagnoseMapper.toDTO(getByName(name));
    }

    @Override
    public Set<DiagnoseEntity> getAll() {
        Set<DiagnoseEntity> all = diagnoseRepository.findAllByDeletedFalse();
        if (all.isEmpty()) {
            throw new NoSuchDiagnoseEntityFoundException("No Diagnoses found!");
        }
        return all;
    }

    @Override
    public Set<DiagnoseDTO> getAllToDTO() {
        return diagnoseMapper.allToDTO(getAll());
    }

    @Override
    public DiagnoseDTO create(CreateDiagnoseDTO createDiagnoseDTO) {
        if (diagnoseRepository.findByName(createDiagnoseDTO.getName()).isPresent()) {
            diagnoseRepository.softCreate(createDiagnoseDTO.getName());
            return diagnoseMapper.toDTO(createDiagnoseDTO);
        }
        return diagnoseMapper.toDTO(
                diagnoseRepository.save(diagnoseMapper.toEntity(createDiagnoseDTO))
        );
    }

    @Override
    public void delete(String name) {
        diagnoseRepository.softDelete(name);
    }

    @Override
    public DiagnoseVisitDTO getDiagnoseVisitCount(String name) {
        return diagnoseMapper.toDTO(
                diagnoseRepository.countVisitsByDiagnoseName(name.toUpperCase())
        );
    }

    @Override
    public DiagnoseIncomeDTO getDiagnoseIncomeByName(String name) {
        return diagnoseMapper.toDTO(
                diagnoseRepository.getTotalIncomeOfDiagnoseName(name)
        );
    }

}
