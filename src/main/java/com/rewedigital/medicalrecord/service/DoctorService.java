package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.UpdateDoctorDTO;
import com.rewedigital.medicalrecord.model.entity.DoctorEntity;
import com.rewedigital.medicalrecord.model.entity.GpEntity;

import java.util.List;

public interface DoctorService {

    DoctorEntity getByUic(String uic);

    GpEntity getByUicGp(String uic);

    DoctorDTO getByUicToDTO(String uic);

    List<DoctorEntity> getAll();

    List<DoctorDTO> getAllToDTO();

    DoctorDTO create(CreateDoctorDTO createDoctorDTO);

    DoctorDTO update(String uic, UpdateDoctorDTO updateDoctorDTO);

    void delete(String uic);

}
