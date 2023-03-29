package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.appointment.AppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.CreateAppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.UpdateAppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.AppointmentEntity;
import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;

import java.util.List;

public interface AppointmentService {

    AppointmentEntity getByUic(String uic);

    AppointmentDTO getByUicToDTO(String uic);

    List<AppointmentEntity> getAll();

    List<AppointmentDTO> getAllToDTO();

    AppointmentDTO create(CreateAppointmentDTO createAppointmentDTO);

    AppointmentDTO update(String uic, UpdateAppointmentDTO updateAppointmentDTO);

    void delete(String uic);

}
