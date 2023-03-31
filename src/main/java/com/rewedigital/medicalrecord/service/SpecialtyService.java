package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.specialty.CreateSpecialtyDTO;
import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;

import java.util.List;

public interface SpecialtyService {

    SpecialtyEntity getByName(String name);

    SpecialtyDTO getByNameToDTO(String name);

    List<SpecialtyEntity> getAllSpecialties();

    List<SpecialtyDTO> getAllSpecialtiesToDTO();

    SpecialtyDTO create(CreateSpecialtyDTO createSpecialtyDTO);

    void delete(String name);

}
