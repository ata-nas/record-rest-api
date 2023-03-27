package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.UpdateDoctorDTO;
import com.rewedigital.medicalrecord.model.entity.DoctorEntity;
import com.rewedigital.medicalrecord.repository.DoctorRepository;
import com.rewedigital.medicalrecord.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public DoctorEntity getByUic(String uic) {
        return null;
    }

    @Override
    public DoctorDTO getByUicToDTO(String uic) {
        return null;
    }

    @Override
    public List<DoctorEntity> getAll() {
        return null;
    }

    @Override
    public List<DoctorDTO> getAllToDTO() {
        return null;
    }

    @Override
    public DoctorDTO create(CreateDoctorDTO createDoctorDTO) {
        return null;
    }

    @Override
    public DoctorDTO update(String uic, UpdateDoctorDTO updateDoctorDTO) {
        return null;
    }

    @Override
    public void delete(String uic) {

    }

}
