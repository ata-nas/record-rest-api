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
    /**
     * This PricingHistoryRepository is here because in this context it is tightly coupled to Appointments.
     * I removed Healthcare agency since not needed for context of app.
     * This pricing history only supports adding and deleting. I prefer to not be able to update but to delete and enter
     * data again since this is very static data and is used to preserve state of price overtime.
     */
    private final PricingHistoryRepository pricingHistoryRepository;
    // TODO make functionality for this! Already have annotations for overlapping.
    private final AppointmentMapper appointmentMapper;
    private final PricingHistoryMapper pricingHistoryMapper;

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

    @Override
    public PricingHistoryEntity getPricingByIssueNo(String issueNo) {
        return pricingHistoryRepository.findByIssueNo(issueNo)
                .orElseThrow(() -> new NoSuchPricingHistoryEntityFoundException("issueNo", issueNo));
    }

    @Override
    public List<PricingHistoryEntity> getAllPricing() {
        List<PricingHistoryEntity> all = pricingHistoryRepository.findAll();
        if (all.isEmpty()) {
            throw new NoSuchPricingHistoryEntityFoundException("No Pricing History found!");
        }
        return all;
    }

    @Override
    public List<PricingHistoryDTO> getAllPricingToDTO() {
        return pricingHistoryMapper.allToDTO(getAllPricing());
    }

    @Override
    public PricingHistoryDTO createPricing(CreatePricingHistoryDTO createPricingHistoryDTO) {
        return pricingHistoryMapper.toDTO(
                pricingHistoryRepository.save(
                        pricingHistoryMapper.toEntity(createPricingHistoryDTO)
                )
        );
    }

    @Override
    public PricingHistoryDTO updatePricing(String issueNo, UpdatePricingHistoryDTO updatePricingHistoryDTO) {
        return pricingHistoryMapper.toDTO(
                pricingHistoryRepository.save(
                        pricingHistoryMapper.update(updatePricingHistoryDTO, getPricingByIssueNo(issueNo))
                )
        );
    }

    // TODO make functionality to add other appointment fees!

}
