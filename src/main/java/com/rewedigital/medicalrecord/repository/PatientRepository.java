package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PatientEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByUic(String uic);

    @Query(
            "SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE :currDate BETWEEN i.startDate AND i.endDate"
    )
    List<PatientEntity> findAllCurrentlyInsured(LocalDate currDate);

    @Query(
            "SELECT DISTINCT p FROM PatientEntity p LEFT JOIN  p.insurances i " +
                    "WHERE :currDate NOT BETWEEN i.startDate AND i.endDate OR i = null"
    )
    List<PatientEntity> findAllCurrentlyNotInsured(LocalDate currDate);

    @Query(
            "SELECT COUNT(DISTINCT p) FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE :currDate BETWEEN i.startDate AND i.endDate"
    )
    long countAllCurrentlyInsured(LocalDate currDate);

    @Query(
            "SELECT COUNT(DISTINCT p) FROM PatientEntity p LEFT JOIN  p.insurances i " +
                    "WHERE :currDate NOT BETWEEN i.startDate AND i.endDate OR i = null"
    )
    long countAllCurrentlyNotInsured(LocalDate currDate);


}
