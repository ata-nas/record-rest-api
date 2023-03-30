package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.AppointmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    Optional<AppointmentEntity> findByUic(String uic);

    @Query(
            "SELECT SUM(p.appointmentFees) FROM AppointmentEntity a JOIN a.price p"
    )
    BigDecimal getTotalIncome();

}
