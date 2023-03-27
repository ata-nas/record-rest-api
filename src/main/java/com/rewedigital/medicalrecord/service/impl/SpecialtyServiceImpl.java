package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.NoSuchSpecialtyEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.specialty.CreateSpecialtyDTO;
import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;
import com.rewedigital.medicalrecord.model.mapper.SpecialtyMapper;
import com.rewedigital.medicalrecord.repository.SpecialtyRepository;
import com.rewedigital.medicalrecord.service.SpecialtyService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    @Override
    public SpecialtyEntity getByName(String name) {
        return specialtyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchSpecialtyEntityFoundException("name", name));
    }

    @Override
    public SpecialtyDTO getByNameToDTO(String name) {
        return specialtyMapper.toDTO(getByName(name));
    }

    @Override
    public List<SpecialtyEntity> getAllSpecialties() {
        List<SpecialtyEntity> all = specialtyRepository.findAll();
        if (all.isEmpty()) {
            throw new NoSuchSpecialtyEntityFoundException("No Specialties found!");
        }
        return all;
    }

    @Override
    public List<SpecialtyDTO> getAllSpecialtiesToDTO() {
        return specialtyMapper.allToDTO(getAllSpecialties());
    }

    @Override
    public SpecialtyDTO createSpecialty(CreateSpecialtyDTO createSpecialtyDTO) {
        return specialtyMapper.toDTO(
                specialtyRepository.save(specialtyMapper.toEntity(createSpecialtyDTO))
        );
    }

    @Override
    public void deleteSpecialtyByName(String name) {
        specialtyRepository.delete(getByName(name));
    }

}
