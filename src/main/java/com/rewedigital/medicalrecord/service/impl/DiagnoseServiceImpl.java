package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.NoSuchDiagnoseEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import com.rewedigital.medicalrecord.model.mapper.DiagnoseMapper;
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
    public DiagnoseEntity getByName(String name) {
        return diagnoseRepository.findByName(name)
                .orElseThrow(() -> new NoSuchDiagnoseEntityFoundException("name", name));
    }

    @Override
    public DiagnoseDTO getByNameToDTO(String name) {
        return diagnoseMapper.toDTO(getByName(name));
    }

    @Override
    public List<DiagnoseEntity> getAll() {
        List<DiagnoseEntity> all = diagnoseRepository.findAll();
        if (all.isEmpty()) {
            throw new NoSuchDiagnoseEntityFoundException("No Diagnoses found!");
        }
        return all;
    }

    @Override
    public List<DiagnoseDTO> getAllToDTO() {
        return diagnoseMapper.allToDTO(getAll());
    }

    @Override
    public DiagnoseDTO create(CreateDiagnoseDTO diagnoseDTO) {
        return diagnoseMapper.toDTO(
                diagnoseRepository.save(diagnoseMapper.toEntity(diagnoseDTO))
        );
    }

    @Override
    public void delete(String name) {
        diagnoseRepository.delete(getByName(name));
    }

}
