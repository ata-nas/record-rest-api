package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PercentageInsuredPatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.CountDoctorIncomeHigherThanDTO;
import com.rewedigital.medicalrecord.service.AppointmentService;
import com.rewedigital.medicalrecord.service.PatientService;
import com.rewedigital.medicalrecord.service.StatsService;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @Override
    public List<PatientDTO> getAllPatientsCurrentlyInsured() {
        return patientService.getAllPatientsCurrentlyInsured();
    }

    @Override
    public List<PatientDTO> getAllPatientsCurrentlyNotInsured() {
        return patientService.getAllPatientsCurrentlyNotInsured();
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyInsured() {
        return patientService.getPercentageCurrentlyInsured();
    }

    @Override
    public PercentageInsuredPatientDTO getPercentageCurrentlyNotInsured() {
        return patientService.getPercentageCurrentlyNotInsured();
    }

    @Override
    public CountDoctorIncomeHigherThanDTO countDoctorsWithHigherIncomeThanGiven(long income) {
        return appointmentService.countDoctorsWithHigherIncomeThanGiven(income);
    }

}
