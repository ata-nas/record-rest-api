package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.UpdateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.stats.CountDoctorIncomeHigherThanDTO;
import com.rewedigital.medicalrecord.model.dto.stats.DoctorIncomeDTO;
import com.rewedigital.medicalrecord.model.entity.DoctorEntity;
import com.rewedigital.medicalrecord.model.entity.GpEntity;

import java.util.Set;

public interface DoctorService {

    DoctorEntity getByUic(String uic);

    GpEntity getByUicGp(String uic);

    DoctorDTO getByUicToDTO(String uic);

    Set<DoctorEntity> getAll();

    Set<DoctorDTO> getAllToDTO();

    DoctorDTO create(CreateDoctorDTO createDoctorDTO);

    DoctorDTO update(String uic, UpdateDoctorDTO updateDoctorDTO);

    void delete(String uic);

    CountDoctorIncomeHigherThanDTO countDoctorsWithHigherIncomeThanGiven(long income);

    DoctorIncomeDTO getDoctorIncomeByUic(String uic);

    DoctorIncomeDTO getDoctorIncomeByUicFromInsuredPatients(String uic);

    DoctorIncomeDTO getDoctorIncomeByUicFromNotInsuredPatients(String uic);

}
