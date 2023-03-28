package com.rewedigital.medicalrecord.model.mapper.util;

import com.rewedigital.medicalrecord.exception.notfound.*;
import com.rewedigital.medicalrecord.model.entity.*;
import com.rewedigital.medicalrecord.repository.*;

import lombok.RequiredArgsConstructor;

import org.mapstruct.MapperConfig;
import org.mapstruct.Named;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@MapperConfig
@RequiredArgsConstructor
public class MapperUtil {

    private final DoctorRepository doctorRepository;
    private final GpRepository gpRepository;
    private final PatientRepository patientRepository;
    private final DiagnoseRepository diagnoseRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PricingHistoryRepository pricingHistoryRepository;

    @Named("toUpper")
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    @Named("findGpByIdCheckTrue")
    public boolean findGpByIdCheckTrue(Long id) {
        return id != null && gpRepository.findById(id).isPresent();
    }

    @Named("findGpByUicCreateUpdate")
    public GpEntity findGpByUicCreateUpdate(String uic) {
        return uic != null ?
                gpRepository.findByUic(uic).orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic)) : null;
    }

    @Named("findDiagnoseByNameCreateUpdate")
    public DiagnoseEntity findDiagnoseByNameCreateUpdate(String name) {
        return diagnoseRepository.findByName(name)
                .orElseThrow(() -> new NoSuchDiagnoseEntityFoundException("name", name));
    } // TODO Remove ?

    @Named("findDoctorByUic")
    public DoctorEntity findDoctorByUic(String uic) {
        return doctorRepository.findByUic(uic)
                .orElseThrow(() -> new NoSuchDoctorEntityFoundException("uic", uic));
    }

    @Named("findPatientByUic")
    public PatientEntity findPatientByUic(String uic) {
        return patientRepository.findByUic(uic)
                .orElseThrow(() -> new NoSuchPatientEntityFoundException("uic", uic));
    }

    @Named("findPricingHistoryInDate")
    public PricingHistoryEntity findPricingHistoryInDate(LocalDate date) {
        return pricingHistoryRepository.findExistingForDateTime(date)
                .orElseThrow(() -> new NoSuchPricingHistoryEntityFoundException("date", date.toString()));
    }

    @Named("findAllDiagnosesByNameCreateUpdate")
    public Set<DiagnoseEntity> findDiagnoseByNameCreateUpdate(Set<String> names) {
        Set<DiagnoseEntity> all = diagnoseRepository.findAllByNameIn(names);
        if (all.isEmpty()) {
            throw new NoSuchDiagnoseEntityFoundException("No Diagnose by given {name} found!");
        }
        return all;
    }

    @Named("findAllSpecialtiesByNameCreateUpdate")
    public Set<SpecialtyEntity> findAllSpecialtiesByNameCreateUpdate(Set<String> names) {
        Set<SpecialtyEntity> result = specialtyRepository.findAllByNameIn(names);
        if (result.isEmpty()) {
            throw new NoSuchSpecialtyEntityFoundException("No Specialties by given {name} found!");
        }
        return result;
    }

}
