package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PatientEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByUic(String uic);

    Optional<PatientEntity> findByUicAndDeletedFalse(String uic);

    Set<PatientEntity> findAllByDeletedFalse();

    @Query("UPDATE PatientEntity p SET p.deleted = true WHERE p.uic = :uic")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softDelete(String uic);

    @Query("UPDATE PatientEntity p SET p.deleted = false WHERE p.uic = :uic")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softCreate(String uic);

    /**
     * All patients that are currently insured, soft-deleted are excluded!
     * @param currDate as of LocalDate.now();
     * @return all patients that are currently insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE p.deleted = FALSE AND :currDate BETWEEN i.startDate AND i.endDate"
    )
    Set<PatientEntity> findAllCurrentlyInsured(LocalDate currDate);

    /**
     * All patients that are currently not insured, soft-deleted are excluded!
     * @param currDate as of LocalDate.now();
     * @return all patients that are currently not insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT p FROM PatientEntity p " +
                    "WHERE p.deleted = FALSE AND p NOT IN (SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i WHERE :currDate BETWEEN i.startDate AND i.endDate)"
    )
    Set<PatientEntity> findAllCurrentlyNotInsured(LocalDate currDate);

    /**
     * Count of all patients that are currently insured, soft-deleted are excluded!
     * @param currDate as of LocalDate.now();
     * @return count of all patients that are currently insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT COUNT(DISTINCT p) FROM PatientEntity p JOIN p.insurances i " +
                    "WHERE p.deleted = FALSE AND :currDate BETWEEN i.startDate AND i.endDate"
    )
    long countAllCurrentlyInsured(LocalDate currDate);

    /**
     * Count of all patients that are currently not insured, soft-deleted are excluded!
     * @param currDate as of LocalDate.now();
     * @return count of all patients that are currently not insured, soft-deleted are excluded!
     */
    @Query(
            "SELECT COUNT(DISTINCT p) FROM PatientEntity p LEFT JOIN  p.insurances i " +
                    "WHERE p.deleted = FALSE AND p NOT IN (SELECT DISTINCT p FROM PatientEntity p JOIN p.insurances i WHERE :currDate BETWEEN i.startDate AND i.endDate)"
    )
    long countAllCurrentlyNotInsured(LocalDate currDate);

    /**
     * Count of the visits of a patient, soft-deleted are considered counted!
     * @param uic of the patient
     * @return count of the visits of a patient, soft-deleted are considered counted!
     */
    @Query(
            "SELECT COUNT(a.patient) FROM AppointmentEntity a " +
                    "WHERE a.patient.deleted = FALSE AND a.patient.uic = :uic"
    )
    long countVisitsByPatientUic(String uic);

    /**
     * Total income generated from patients who were insured at time of appointment, soft-deleted are included!
     * @return total income generated from patients who were insured at time of appointment, soft-deleted are included!
     */
    @Query(
            "SELECT SUM(a.price.appointmentFees) FROM AppointmentEntity a LEFT JOIN a.price p LEFT JOIN a.patient pa LEFT JOIN pa.insurances i " +
                    "WHERE a.date BETWEEN i.startDate AND i.endDate"
    )
    BigDecimal totalIncomeFromInsured();

    /**
     * Total income generated from patients who were not insured at time of appointment, soft-deleted are included!
     * @return total income generated from patients who were not insured at time of appointment, soft-deleted are included!
     */
    @Query(
            "SELECT SUM(a.price.appointmentFees) FROM AppointmentEntity a LEFT JOIN a.price p LEFT JOIN a.patient pa LEFT JOIN pa.insurances i " +
                    "WHERE i.startDate IS NULL OR i.endDate IS NULL OR a.date NOT BETWEEN i.startDate AND i.endDate"
    )
    BigDecimal totalIncomeFromNotInsured();

}
