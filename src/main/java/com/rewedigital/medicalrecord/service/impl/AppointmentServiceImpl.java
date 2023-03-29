package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchAppointmentEntityFoundException;
import com.rewedigital.medicalrecord.exception.notfound.NoSuchPricingHistoryEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.appointment.AppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.CreateAppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.UpdateAppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.AppointmentEntity;
import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;
import com.rewedigital.medicalrecord.model.mapper.AppointmentMapper;
import com.rewedigital.medicalrecord.model.mapper.PricingHistoryMapper;
import com.rewedigital.medicalrecord.repository.AppointmentRepository;
import com.rewedigital.medicalrecord.repository.PricingHistoryRepository;
import com.rewedigital.medicalrecord.service.AppointmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentEntity getByUic(String uic) {
        return appointmentRepository.findByUic(uic)
                .orElseThrow(() -> new NoSuchAppointmentEntityFoundException("uic", uic));
    }

    @Override
    public AppointmentDTO getByUicToDTO(String uic) {
        return appointmentMapper.toDTO(getByUic(uic));
    }

    @Override
    public List<AppointmentEntity> getAll() {
        List<AppointmentEntity> all = appointmentRepository.findAll();
        if (all.isEmpty()) {
            throw new NoSuchAppointmentEntityFoundException("No Appointments found!");
        }
        return all;
    }

    @Override
    public List<AppointmentDTO> getAllToDTO() {
        return appointmentMapper.allToDTO(getAll());
    }

    @Override
    public AppointmentDTO create(CreateAppointmentDTO createAppointmentDTO) {
        return appointmentMapper.toDTO(
                appointmentRepository.save(
                        appointmentMapper.toEntity(createAppointmentDTO)
                )
        );
    }

    @Override
    public AppointmentDTO update(String uic, UpdateAppointmentDTO updateAppointmentDTO) {
        return appointmentMapper.toDTO(
                appointmentRepository.save(
                        appointmentMapper.toEntity(updateAppointmentDTO, getByUic(uic))
                )
        );
    }

    @Override
    public void delete(String uic) {
        appointmentRepository.delete(getByUic(uic));
    }



    // TODO make functionality to add other appointment fees!

}
