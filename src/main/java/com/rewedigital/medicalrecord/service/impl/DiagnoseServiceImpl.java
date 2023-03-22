package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.model.dto.DiagnoseDTO;
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
    public List<DiagnoseEntity> getAllDiagnoses() {
        return diagnoseRepository.findAll();
    }

    @Override
    public List<DiagnoseDTO> getAllDiagnosesToDTO() {
        return diagnoseMapper.allDiagnoseEntityToDiagnoseDTO(getAllDiagnoses());
    }

    @Override
    public DiagnoseDTO createDiagnose(DiagnoseDTO diagnoseDTO) {
        return diagnoseMapper.diagnoseEntityToDiagnoseDTO(
                diagnoseRepository.save(diagnoseMapper.diagnoseDTOToDiagnoseEntity(diagnoseDTO)));
    }

}
