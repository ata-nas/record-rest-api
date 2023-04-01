package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.specialty.CreateSpecialtyDTO;
import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;

import java.util.Set;

public interface SpecialtyService {

    SpecialtyEntity getByName(String name);

    SpecialtyDTO getByNameToDTO(String name);

    Set<SpecialtyEntity> getAll();

    Set<SpecialtyDTO> getAllToDTO();

    SpecialtyDTO create(CreateSpecialtyDTO createSpecialtyDTO);

    void delete(String name);

}
