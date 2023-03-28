package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PatientInsuranceHistoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInsuranceHistoryRepository extends JpaRepository<PatientInsuranceHistoryEntity, Long> {
} // TODO Remove ?
