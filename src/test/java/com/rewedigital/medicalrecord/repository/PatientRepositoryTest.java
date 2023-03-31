package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void init() {
        initDb();
    }

    @Test
    public void testFindByUic_ReturnsOptionalWithPatientWithGivenUic(){
        String expected = "000001";

        Optional<PatientEntity> optActual = patientRepository.findByUic(expected);
        assertThat(optActual.isPresent()).isTrue();

        PatientEntity actual = optActual.get();
        assertThat(actual.getUic()).isEqualTo(expected);
    }

    @Test
    public void testFindByUicAndDeletedFalse_ReturnsOptionalWithPatientWithGivenUicIfDeletedFalse(){
        String expected = "000001";

        Optional<PatientEntity> optActual = patientRepository.findByUicAndDeletedFalse(expected);
        assertThat(optActual.isPresent()).isTrue();

        PatientEntity actual = optActual.get();
        assertThat(actual.getUic()).isEqualTo(expected);
    }

    @Test
    public void testFindAllByDeletedFalse_ReturnsCollectionContainingPatientsWhereDeletedIsFalse(){
        PatientInsuranceHistoryEntity insurance = new PatientInsuranceHistoryEntity();
        ReflectionTestUtils.setField(insurance, "startDate", LocalDate.of(2000, 1, 1));
        ReflectionTestUtils.setField(insurance, "endDate", LocalDate.of(2050, 1, 1));

        PatientInsuranceHistoryEntity patientInsuranceHistoryEntity = testEntityManager.persistAndFlush(insurance);

        Set<PatientInsuranceHistoryEntity> insurances = new LinkedHashSet<>();
        insurances.add(patientInsuranceHistoryEntity);

        PatientEntity patient = new PatientEntity();
        ReflectionTestUtils.setField(patient, "uic", "000001");
        ReflectionTestUtils.setField(patient, "name", "Petar");
        ReflectionTestUtils.setField(patient, "gp", null);
        ReflectionTestUtils.setField(patient, "insurances", insurances);
        ReflectionTestUtils.setField(patient, "deleted", Boolean.FALSE);

        Set<PatientEntity> expected = new LinkedHashSet<>();
        expected.add(patient);

        Set<PatientEntity> actual = patientRepository.findAllByDeletedFalse();
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSoftDelete_UpdatesFieldDeletedToFalseOnPatientWithUic() {
        patientRepository.softDelete("000001");

        Optional<PatientEntity> actual = patientRepository.findByUic("000001");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().isDeleted()).isTrue();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSoftCreate_UpdatesFieldDeletedToTrueOnPatientWithUic() {
        patientRepository.softCreate("000002");

        Optional<PatientEntity> actual = patientRepository.findByUic("000002");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().isDeleted()).isFalse();
    }

    @Test
    public void testFindAllCurrentlyInsured_ReturnsCollectionWithPatientsWhereDeletedIsFalseAndAreCurrentlyInsured() {
        PatientInsuranceHistoryEntity insurance = new PatientInsuranceHistoryEntity();
        ReflectionTestUtils.setField(insurance, "startDate", LocalDate.of(2000, 1, 1));
        ReflectionTestUtils.setField(insurance, "endDate", LocalDate.of(2050, 1, 1));

        PatientInsuranceHistoryEntity patientInsuranceHistoryEntity = testEntityManager.persistAndFlush(insurance);

        Set<PatientInsuranceHistoryEntity> insurances = new LinkedHashSet<>();
        insurances.add(patientInsuranceHistoryEntity);

        PatientEntity patient = new PatientEntity();
        ReflectionTestUtils.setField(patient, "uic", "000001");
        ReflectionTestUtils.setField(patient, "name", "Petar");
        ReflectionTestUtils.setField(patient, "gp", null);
        ReflectionTestUtils.setField(patient, "insurances", insurances);
        ReflectionTestUtils.setField(patient, "deleted", Boolean.FALSE);

        Set<PatientEntity> expected = new LinkedHashSet<>();
        expected.add(patient);

        Set<PatientEntity> actual = patientRepository.findAllCurrentlyInsured(LocalDate.of(2020, 1, 1));
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    public void testFindAllCurrentlyNotInsured_ReturnsCollectionWithPatientsWhereDeletedIsFalseAndAreCurrentlyNotInsured() {
        PatientInsuranceHistoryEntity insurance = new PatientInsuranceHistoryEntity();
        ReflectionTestUtils.setField(insurance, "startDate", LocalDate.of(2000, 1, 1));
        ReflectionTestUtils.setField(insurance, "endDate", LocalDate.of(2050, 1, 1));

        PatientInsuranceHistoryEntity patientInsuranceHistoryEntity = testEntityManager.persistAndFlush(insurance);

        Set<PatientInsuranceHistoryEntity> insurances = new LinkedHashSet<>();
        insurances.add(patientInsuranceHistoryEntity);

        PatientEntity patient = new PatientEntity();
        ReflectionTestUtils.setField(patient, "uic", "000001");
        ReflectionTestUtils.setField(patient, "name", "Petar");
        ReflectionTestUtils.setField(patient, "gp", null);
        ReflectionTestUtils.setField(patient, "insurances", insurances);
        ReflectionTestUtils.setField(patient, "deleted", Boolean.FALSE);

        Set<PatientEntity> expected = new LinkedHashSet<>();
        expected.add(patient);

        Set<PatientEntity> actual = patientRepository.findAllCurrentlyNotInsured(LocalDate.of(1999, 1, 1));
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    public void testCountAllCurrentlyInsured_ReturnsCountOfAllPatientsWhereDeletedIsFalseThatAreCurrentlyInsured() {
        long expected = 1L;
        long actual = patientRepository.countAllCurrentlyInsured(LocalDate.of(2020, 1, 1));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCountAllCurrentlyNotInsured_ReturnsCountOfAllPatientsWhereDeletedIsFalseThatAreCurrentlyNotInsured() {
        long expected = 1L;
        long actual = patientRepository.countAllCurrentlyNotInsured(LocalDate.of(1999, 1, 1));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testCountVisitsByPatientUic_ReturnsCountOfAllVisitsOfPatientWithUicWhereDeletedIsFalse() {
        long expected = 1L;
        long actual = patientRepository.countVisitsByPatientUic("000001");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testTotalIncomeFromInsured_ReturnsTotalIncomeFromAppointmentsWherePatientHadInsuranceAtDateOfAppointment() {
        double expected = 10.0;
        BigDecimal actual = patientRepository.totalIncomeFromInsured();
        if (actual == null) {
            actual = BigDecimal.ZERO;
        }
        assertThat(actual.doubleValue()).isEqualTo(expected);
    }

    @Test
    public void testTotalIncomeFromNotInsured_ReturnsTotalIncomeFromAppointmentsWherePatientHadNoInsuranceAtDateOfAppointment() {
        double expected = 0.0;
        BigDecimal actual = patientRepository.totalIncomeFromNotInsured();
        if (actual == null) {
            actual = BigDecimal.ZERO;
            assertThat(actual.doubleValue()).isEqualTo(expected);
        }
        assertThat(actual.doubleValue()).isEqualTo(expected);
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

        testEntityManager.persistAndFlush(doctorSoftDeleted);

        // Init Patients
        PatientInsuranceHistoryEntity insurance = new PatientInsuranceHistoryEntity();
        ReflectionTestUtils.setField(insurance, "startDate", LocalDate.of(2000, 1, 1));
        ReflectionTestUtils.setField(insurance, "endDate", LocalDate.of(2050, 1, 1));

        PatientInsuranceHistoryEntity patientInsuranceHistoryEntity = testEntityManager.persistAndFlush(insurance);

        Set<PatientInsuranceHistoryEntity> insurances = new LinkedHashSet<>();
        insurances.add(patientInsuranceHistoryEntity);

        PatientEntity patient = new PatientEntity();
        ReflectionTestUtils.setField(patient, "uic", "000001");
        ReflectionTestUtils.setField(patient, "name", "Petar");
        ReflectionTestUtils.setField(patient, "gp", null);
        ReflectionTestUtils.setField(patient, "insurances", insurances);
        ReflectionTestUtils.setField(patient, "deleted", Boolean.FALSE);

        PatientEntity patientEntity = testEntityManager.persistAndFlush(patient);

        PatientEntity patientSoftDeleted = new PatientEntity();
        ReflectionTestUtils.setField(patientSoftDeleted, "uic", "000002");
        ReflectionTestUtils.setField(patientSoftDeleted, "name", "Ivan");
        ReflectionTestUtils.setField(patientSoftDeleted, "gp", null);
        ReflectionTestUtils.setField(patientSoftDeleted, "insurances", null);
        ReflectionTestUtils.setField(patientSoftDeleted, "deleted", Boolean.TRUE);

        testEntityManager.persistAndFlush(patientSoftDeleted);

        // Init Diagnoses
        DiagnoseEntity diagnose = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnose, "name", "HEALTHY");
        ReflectionTestUtils.setField(diagnose, "deleted", Boolean.FALSE);

        DiagnoseEntity diagnoseEntity = testEntityManager.persistAndFlush(diagnose);

        DiagnoseEntity diagnoseSoftDeleted = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnoseSoftDeleted, "name", "FLU");
        ReflectionTestUtils.setField(diagnoseSoftDeleted, "deleted", Boolean.TRUE);

        testEntityManager.persistAndFlush(diagnoseSoftDeleted);

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
