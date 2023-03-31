package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.DoctorEntity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByUic(String uic);

    Optional<DoctorEntity> findByUicAndDeletedFalse(String uic);

//    @Query(
//            "SELECT d FROM DoctorEntity d " +
//                    "WHERE d.deleted = false"
//    )
    Set<DoctorEntity> findAllByDeletedFalse();

    @Query("UPDATE DoctorEntity d SET d.deleted = true WHERE d.uic = :uic")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softDelete(String uic);

    @Query("UPDATE DoctorEntity d SET d.deleted = false WHERE d.uic = :uic")
    @Modifying(clearAutomatically = true)
    @Transactional
    void softCreate(String uic);

    /**
     * Get a Set of all doctors who have ever made an appointment, soft-deleted are included!
     * @return Set of all doctors who have ever made an appointment, soft-deleted are included!
     */
    @Query(
            "SELECT DISTINCT d from AppointmentEntity a JOIN a.doctor d "
    )
    Set<DoctorEntity> findAllDoctorsWhoHaveMadeAppointments();

    /**
     * Total income generated from patients who were insured at time of appointment, soft-deleted patients are included!
     * @return total income generated from patients who were insured at time of appointment, soft-deleted patients are included!
     */
    @Query(
            "SELECT SUM(a.price.appointmentFees) FROM AppointmentEntity a LEFT JOIN a.price p LEFT JOIN a.patient pa LEFT JOIN pa.insurances i " +
                    "WHERE a.date BETWEEN i.startDate AND i.endDate AND a.doctor.uic = :uic"
    )
    BigDecimal totalIncomeFromInsured(String uic);

    /**
     * Total income generated from patients who were not insured at time of appointment, soft-deleted patients are included!
     * @return total income generated from patients who were not insured at time of appointment, soft-deleted patients are included!
     */
    @Query(
            "SELECT SUM(a.price.appointmentFees) FROM AppointmentEntity a LEFT JOIN a.price p LEFT JOIN a.patient pa LEFT JOIN pa.insurances i " +
                    "WHERE (a.doctor.uic = :uic AND a.date NOT BETWEEN i.startDate AND i.endDate) OR (a.doctor.uic = :uic AND i.startDate IS NULL) OR (a.doctor.uic = :uic AND i.endDate IS NULL)"
    )
    BigDecimal totalIncomeFromNotInsured(String uic);

}
