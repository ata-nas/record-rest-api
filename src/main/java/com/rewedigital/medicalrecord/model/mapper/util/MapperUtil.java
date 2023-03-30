package com.rewedigital.medicalrecord.model.mapper.util;

import com.rewedigital.medicalrecord.exception.notfound.*;
import com.rewedigital.medicalrecord.model.entity.*;
import com.rewedigital.medicalrecord.repository.*;

import lombok.RequiredArgsConstructor;

import org.mapstruct.MapperConfig;
import org.mapstruct.Named;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

/**
 * Utility for MapStruct. Here are methods that define what a validly mapped Entity means in my domain.
 * Most of the methods (defined - "...CreateUpdate") that pull from repositories are pulling Entities that are
 * not soft-deleted since that constitutes the current state of the application.
 */
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

    @Named("findPricingHistoryInDate")
    public PricingHistoryEntity findPricingHistoryInDate(LocalDate date) {
        return pricingHistoryRepository.findExistingForDate(date)
                .orElseThrow(() -> new NoSuchPricingHistoryEntityFoundException("date", date.toString()));
    }

    @Named("findGpByUicCreateUpdate")
    public GpEntity findGpByUicCreateUpdate(String uic) {
        return uic != null ?
                gpRepository.findByUicAndDeletedFalse(uic).orElseThrow(() -> new NoSuchGpEntityFoundException("uic", uic)) : null;
    }

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
        Set<DiagnoseEntity> all = diagnoseRepository.findAllByNameInAndDeletedFalse(names);
        if (all.isEmpty()) {
            throw new NoSuchDiagnoseEntityFoundException("No Diagnose by given {name} found!");
        }
        return all;
    }

    @Named("findAllSpecialtiesByNameCreateUpdate")
    public Set<SpecialtyEntity> findAllSpecialtiesByNameCreateUpdate(Set<String> names) {
        if (names == null) {
            return null;
        }
        Set<SpecialtyEntity> result = specialtyRepository.findAllByNameInAndDeletedFalse(names);
        if (result.isEmpty()) {
            throw new NoSuchSpecialtyEntityFoundException("No Specialties by given {name} found!");
        }
        return result;
    }

}
