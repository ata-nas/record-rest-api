package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.AppointmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query(
            "SELECT DISTINCT a FROM AppointmentEntity a JOIN a.patient p JOIN p.insurances ih " +
                    "WHERE a.dateTime BETWEEN ih.startDateTime AND ih.endDateTime"
    )
    Set<AppointmentEntity> findAllWherePatientInsured(); // TODO Example how to aggregate data for stats later!

}
