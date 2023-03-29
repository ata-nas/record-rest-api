package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.DoctorEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<DoctorEntity> findByUic(String uic);

    Optional<DoctorEntity> findByUicAndDeletedFalse(String uic);

    @Query(
            "SELECT d FROM DoctorEntity d " +
                    "WHERE d.deleted = false"
    )
    List<DoctorEntity> findAllDeletedFalse();

    Set<DoctorEntity> findAllByUicIn(Set<String> uic);

    @Query("UPDATE DoctorEntity d SET d.deleted = true WHERE d.uic = :uic")
    @Modifying
    void softDelete(String uic);

    @Query("UPDATE DoctorEntity d SET d.deleted = false WHERE d.uic = :uic")
    @Modifying
    void softCreate(String uic);

}
