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
                gpRepository.findByUicAndDeletedFalse(uic).orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic)) : null;
    }

    @Named("findDiagnoseByNameCreateUpdate")
    public DiagnoseEntity findDiagnoseByNameCreateUpdate(String name) {
        return diagnoseRepository.findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new NoSuchDiagnoseEntityFoundException("name", name));
    } // TODO Remove ?

    @Named("findDoctorByUicCreateUpdate")
    public DoctorEntity findDoctorByUicCreateUpdate(String uic) {
        return doctorRepository.findByUicAndDeletedFalse(uic)
                .orElseThrow(() -> new NoSuchDoctorEntityFoundException("uic", uic));
    }

    @Named("findPatientByUicCreateUpdate")
    public PatientEntity findPatientByUicCreateUpdate(String uic) {
        return patientRepository.findByUicAndDeletedFalse(uic)
                .orElseThrow(() -> new NoSuchPatientEntityFoundException("uic", uic));
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
        Set<SpecialtyEntity> result = specialtyRepository.findAllByNameInAndDeletedFalse(names);
        if (result.isEmpty()) {
            throw new NoSuchSpecialtyEntityFoundException("No Specialties by given {name} found!");
        }
        return result;
    }

    @Named("findPricingHistoryInDate")
    public PricingHistoryEntity findPricingHistoryInDate(LocalDate date) {
        return pricingHistoryRepository.findExistingForDateTime(date)
                .orElseThrow(() -> new NoSuchPricingHistoryEntityFoundException("date", date.toString()));
    }

}
