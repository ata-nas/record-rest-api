package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class DiagnoseRepositoryTest {

    @Autowired
    private DiagnoseRepository diagnoseRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void init() {
        initDb();
    }

    @Test
    public void testFindByName_ReturnsOptionalWithDiagnoseWithGivenName(){
        String expected = "HEALTHY";

        Optional<DiagnoseEntity> optActual = diagnoseRepository.findByName(expected);
        assertThat(optActual.isPresent()).isTrue();

        DiagnoseEntity actual = optActual.get();
        assertThat(actual.getName()).isEqualTo(expected);
    }

    @Test
    public void testFindByNameAndDeletedFalse_ReturnsOptionalWIthDiagnoseWithGivenNameIfDeletedFalse(){
        String expected = "HEALTHY";

        Optional<DiagnoseEntity> optActual = diagnoseRepository.findByNameAndDeletedFalse(expected);
        assertThat(optActual.isPresent()).isTrue();

        DiagnoseEntity actual = optActual.get();
        assertThat(actual.getName()).isEqualTo(expected);
    }

    @Test
    public void testFindAllByDeletedFalse_ReturnsCollectionContainingDiagnosesWhereDeletedIsFalse(){
        DiagnoseEntity diagnose = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnose, "name", "HEALTHY");

        Set<DiagnoseEntity> expected = new LinkedHashSet<>();
        expected.add(diagnose);

        Set<DiagnoseEntity> actual = diagnoseRepository.findAllByDeletedFalse();
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    public void testFindAllByNameInAndDeletedFalse_ReturnsCollectionContainingDiagnosesWithGivenNamesWhereDeletedIsFalse(){
        DiagnoseEntity diagnose = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnose, "name", "HEALTHY");
        ReflectionTestUtils.setField(diagnose, "deleted", Boolean.FALSE);

        Set<DiagnoseEntity> expected = new LinkedHashSet<>();
        expected.add(diagnose);

        Set<DiagnoseEntity> actual = diagnoseRepository.findAllByNameInAndDeletedFalse(Set.of(diagnose.getName()));
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    public void testSoftDelete_UpdatesFieldDeletedToFalseOnDiagnoseWithName() {
        diagnoseRepository.softDelete("HEALTHY");

        Optional<DiagnoseEntity> actual = diagnoseRepository.findByName("HEALTHY");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().isDeleted()).isTrue();
    }

    @Test
    public void testSoftCreate_UpdatesFieldDeletedToTrueOnDiagnoseWithName() {
        diagnoseRepository.softCreate("FLU");

        Optional<DiagnoseEntity> actual = diagnoseRepository.findByName("FLU");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().isDeleted()).isFalse();
    }

    @Test
    public void testCountVisitsByDiagnoseName_ReturnsCountOfVisitsWhereGivenDiagnoseIsChosen() {
        long expected = 1L;
        long actual = diagnoseRepository.countVisitsByDiagnoseName("HEALTHY");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetTotalIncomeOfDiagnoseName_ReturnsTheTotalIncomeMadeWhereGivenDiagnoseIsChosen() {
        double expected = 10.0;
        double actual = diagnoseRepository.getTotalIncomeOfDiagnoseName("HEALTHY").doubleValue();
        assertThat(actual).isEqualTo(expected);
    }

    private void initDb() {
        // Init Doctors
        DoctorEntity doctor = new DoctorEntity();
        ReflectionTestUtils.setField(doctor, "uic", "0001");
        ReflectionTestUtils.setField(doctor, "name", "Georgiev");
        ReflectionTestUtils.setField(doctor, "birthDate", LocalDate.of(1990, 1, 1));
        ReflectionTestUtils.setField(doctor, "deleted", Boolean.FALSE);

        DoctorEntity doctorEntity = testEntityManager.persistAndFlush(doctor);

        DoctorEntity doctorSoftDeleted = new DoctorEntity();
        ReflectionTestUtils.setField(doctorSoftDeleted, "uic", "0002");
        ReflectionTestUtils.setField(doctorSoftDeleted, "name", "Dimitrov");
        ReflectionTestUtils.setField(doctorSoftDeleted, "birthDate", LocalDate.of(1990, 1, 1));
        ReflectionTestUtils.setField(doctorSoftDeleted, "deleted", Boolean.TRUE);

        DoctorEntity doctorEntitySoftDeleted = testEntityManager.persistAndFlush(doctorSoftDeleted);

        // Init Patients
        PatientEntity patient = new PatientEntity();
        ReflectionTestUtils.setField(patient, "uic", "000001");
        ReflectionTestUtils.setField(patient, "name", "Petar");
        ReflectionTestUtils.setField(patient, "gp", null);
        ReflectionTestUtils.setField(patient, "deleted", Boolean.FALSE);

        PatientEntity patientEntity = testEntityManager.persistAndFlush(patient);

        PatientEntity patientSoftDeleted = new PatientEntity();
        ReflectionTestUtils.setField(patientSoftDeleted, "uic", "000002");
        ReflectionTestUtils.setField(patientSoftDeleted, "name", "Ivan");
        ReflectionTestUtils.setField(patientSoftDeleted, "gp", null);
        ReflectionTestUtils.setField(patientSoftDeleted, "deleted", Boolean.TRUE);

        PatientEntity patientEntitySoftDeleted = testEntityManager.persistAndFlush(patientSoftDeleted);

        // Init Diagnoses
        DiagnoseEntity diagnose = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnose, "name", "HEALTHY");
        ReflectionTestUtils.setField(diagnose, "deleted", Boolean.FALSE);

        DiagnoseEntity diagnoseEntity = testEntityManager.persistAndFlush(diagnose);

        DiagnoseEntity diagnoseSoftDeleted = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnoseSoftDeleted, "name", "FLU");
        ReflectionTestUtils.setField(diagnoseSoftDeleted, "deleted", Boolean.TRUE);

        DiagnoseEntity diagnoseEntitySoftDeleted = testEntityManager.persistAndFlush(diagnoseSoftDeleted);

        // Init PricingHistory
        PricingHistoryEntity pricing = new PricingHistoryEntity();
        ReflectionTestUtils.setField(pricing,"issueNo", "1");
        ReflectionTestUtils.setField(pricing,"appointmentFees", BigDecimal.TEN);
        ReflectionTestUtils.setField(pricing,"fromDate", LocalDate.of(1990, 1, 1));

        PricingHistoryEntity pricingHistoryEntity = testEntityManager.persistAndFlush(pricing);

        // Init Appointments
        LinkedHashSet<DiagnoseEntity> diagnoses = new LinkedHashSet<>();
        diagnoses.add(diagnoseEntity);

        AppointmentEntity appointment = new AppointmentEntity();
        ReflectionTestUtils.setField(appointment, "uic", "0001");
        ReflectionTestUtils.setField(appointment, "patient", patientEntity);
        ReflectionTestUtils.setField(appointment, "price", pricingHistoryEntity);
        ReflectionTestUtils.setField(appointment, "description", "descr");
        ReflectionTestUtils.setField(appointment, "doctor", doctorEntity);
        ReflectionTestUtils.setField(appointment, "date", LocalDate.of(2020, 1, 1));
        ReflectionTestUtils.setField(appointment, "diagnoses", diagnoses);

        testEntityManager.persistAndFlush(appointment);
    }

}