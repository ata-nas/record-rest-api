package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchDoctorEntityFoundException;
import com.rewedigital.medicalrecord.exception.notfound.NoSuchGpEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.UpdateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.stats.CountDoctorIncomeHigherThanDTO;
import com.rewedigital.medicalrecord.model.dto.stats.DoctorIncomeDTO;
import com.rewedigital.medicalrecord.model.entity.DoctorEntity;
import com.rewedigital.medicalrecord.model.entity.GpEntity;
import com.rewedigital.medicalrecord.model.mapper.DoctorMapper;
import com.rewedigital.medicalrecord.repository.DoctorRepository;
import com.rewedigital.medicalrecord.repository.GpRepository;
import com.rewedigital.medicalrecord.service.DoctorService;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    /**
     * This is here because GpEntity inherits DoctorEntity and GpEntity exists only as a flag and DB constraint. So I manage from here.
     */
    private final GpRepository gpRepository;

    @Override
    public DoctorEntity getByUic(String uic) {
        return doctorRepository.findByUicAndDeletedFalse(uic)
                .orElseThrow(() -> new NoSuchDoctorEntityFoundException("uic", uic));
    }

    @Override
    public GpEntity getByUicGp(String uic) {
        return gpRepository.findByUicAndDeletedFalse(uic)
                .orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic));
    }

    @Override
    public DoctorDTO getByUicToDTO(String uic) {
        return doctorMapper.toDTO(getByUic(uic));
    }

    @Override
    public List<DoctorEntity> getAll() {
        List<DoctorEntity> all = doctorRepository.findAllDeletedFalse();
        if (all.isEmpty()) {
            throw new NoSuchDoctorEntityFoundException("No Doctors found!");
        }
        return all;
    }

    @Override
    public List<DoctorDTO> getAllToDTO() {
        return doctorMapper.allToDTO(getAll());
    }

    @Override
    public DoctorDTO create(CreateDoctorDTO createDoctorDTO) {
        return createDoctorDTO.isGp() ?
                createNewDoctorGp(createDoctorDTO) : createNewDoctor(createDoctorDTO);
    }

    private DoctorDTO createNewDoctor(CreateDoctorDTO createDoctorDTO) {
        if (doctorRepository.findByUic(createDoctorDTO.getUic()).isPresent()) {
            doctorRepository.softCreate(createDoctorDTO.getUic());
            return updateDoctor(createDoctorDTO.getUic(), doctorMapper.toDTO(createDoctorDTO));
        }
        return doctorMapper.toDTO(
                doctorRepository.save(doctorMapper.toEntity(createDoctorDTO))
        );
    }

    private DoctorDTO createNewDoctorGp(CreateDoctorDTO createDoctorDTO) {
        if (gpRepository.findByUic(createDoctorDTO.getUic()).isPresent()) {
            doctorRepository.softCreate(createDoctorDTO.getUic());
            return updateDoctorGp(createDoctorDTO.getUic(), doctorMapper.toDTO(createDoctorDTO));
        }
        return doctorMapper.toDTO(
                doctorRepository.save(doctorMapper.toEntityGp(createDoctorDTO))
        );
    }

    @Override
    public DoctorDTO update(String uic, UpdateDoctorDTO updateDoctorDTO) {
        return doctorIsGp(uic) ?
                updateDoctorGp(uic, updateDoctorDTO) : updateDoctor(uic, updateDoctorDTO);
    }

    private DoctorDTO updateDoctor(String uic, UpdateDoctorDTO updateDoctorDTO) {
        return doctorMapper.toDTO(
                doctorRepository.save(
                        doctorMapper.toEntity(updateDoctorDTO, getByUic(uic))
                )
        );
    }

    private DoctorDTO updateDoctorGp(String uic, UpdateDoctorDTO updateDoctorDTO) {
        return doctorMapper.toDTO(
                doctorRepository.save(
                        doctorMapper.toEntityGp(updateDoctorDTO, getByUicGp(uic))
                )
        );
    }

    private boolean doctorIsGp(String uic) {
        return gpRepository.findByUicAndDeletedFalse(uic).isPresent();
    }

    @Override
    public void delete(String uic) {
        doctorRepository.softDelete(uic);
    }

    @Override
    public CountDoctorIncomeHigherThanDTO countDoctorsWithHigherIncomeThanGiven(long income) {
        Set<DoctorEntity> doctors = doctorRepository.findAllDoctorsWhoHaveMadeAppointments();
        if (doctors.isEmpty()) {
            throw new NoSuchDoctorEntityFoundException("No Doctors found!");
        }
        return doctorMapper.toDTO(doctors.stream()
                .filter(doctorEntity -> doctorEntity.getAppointmentEntities()
                        .stream()
                        .map(appointmentEntity -> appointmentEntity.getPrice().getAppointmentFees())
                        .reduce(BigDecimal.ZERO, BigDecimal::add).longValue() > income)
                .count()
        );
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUic(String uic) {
        return doctorMapper.toDTO(getByUic(uic).getAppointmentEntities()
                .stream()
                .map(appointmentEntity -> appointmentEntity.getPrice().getAppointmentFees())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUicFromInsuredPatients(String uic) {
        BigDecimal total = doctorRepository.totalIncomeFromInsured(uic);
        return total == null ? doctorMapper.toDTO(BigDecimal.ZERO) : doctorMapper.toDTO(total);
    }

    @Override
    public DoctorIncomeDTO getDoctorIncomeByUicFromNotInsuredPatients(String uic) {
        BigDecimal total = doctorRepository.totalIncomeFromNotInsured(uic);
        return total == null ? doctorMapper.toDTO(BigDecimal.ZERO) : doctorMapper.toDTO(total);
    }

}
