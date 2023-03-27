package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.DoctorEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByUic(String uic);

}
