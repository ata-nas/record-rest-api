package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PatientInsuranceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PatientInsuranceHistoryRepository extends JpaRepository<PatientInsuranceHistoryEntity, Long> {

    @Query(
            "SELECT DISTINCT ih FROM PatientInsuranceHistoryEntity ih " +
                    "WHERE :startDate BETWEEN ih.startDateTime AND ih.endDateTime "
    )
    Optional<PatientInsuranceHistoryEntity> findOverlappingStartDate(LocalDateTime startDate);

}
