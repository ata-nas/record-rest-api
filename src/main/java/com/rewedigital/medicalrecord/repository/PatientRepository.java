package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByUic(String uic);

}
