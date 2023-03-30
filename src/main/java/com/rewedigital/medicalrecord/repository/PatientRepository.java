package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PatientEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByUic(String uic);

    Optional<PatientEntity> findByUicAndDeletedFalse(String uic);

    @Query(
            "SELECT p FROM PatientEntity p " +
                    "WHERE p.deleted = false"
    )
    List<PatientEntity> findAllDeletedFalse();

//    Set<PatientEntity> findAllByUicIn(Set<String> uic);

    @Query("UPDATE PatientEntity p SET p.deleted = true WHERE p.uic = :uic")
    @Modifying
    void softDelete(String uic);

    @Query("UPDATE PatientEntity p SET p.deleted = false WHERE p.uic = :uic")
    @Modifying
    void softCreate(String uic);

    @Query(
            "SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE p.deleted = FALSE AND :currDate BETWEEN i.startDate AND i.endDate"
    )
    List<PatientEntity> findAllCurrentlyInsured(LocalDate currDate);

    @Query(
            "SELECT p FROM PatientEntity p " +
                    "WHERE p.deleted = FALSE AND p NOT IN (SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i WHERE :currDate BETWEEN i.startDate AND i.endDate)"
    )
    List<PatientEntity> findAllCurrentlyNotInsured(LocalDate currDate);

    @Query(
            "SELECT COUNT(DISTINCT p) FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE p.deleted = FALSE AND :currDate BETWEEN i.startDate AND i.endDate"
    )
    long countAllCurrentlyInsured(LocalDate currDate);

    @Query(
            "SELECT COUNT(DISTINCT p) FROM PatientEntity p LEFT JOIN  p.insurances i " +
                    "WHERE p.deleted = FALSE AND p NOT IN (SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i WHERE :currDate BETWEEN i.startDate AND i.endDate)"
    )
    long countAllCurrentlyNotInsured(LocalDate currDate);

}
