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
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void init() {
        initDb();
    }

    @Test
    public void testFindByUic_ReturnsOptionalWithDoctorWithGivenUic(){
        String expected = "0001";

        Optional<DoctorEntity> optActual = doctorRepository.findByUic(expected);
        assertThat(optActual.isPresent()).isTrue();

        DoctorEntity actual = optActual.get();
        assertThat(actual.getUic()).isEqualTo(expected);
    }

    @Test
    public void testFindByUicAndDeletedFalse_ReturnsOptionalWithDoctorWithGivenUicIfDeletedFalse(){
        String expected = "0001";

        Optional<DoctorEntity> optActual = doctorRepository.findByUicAndDeletedFalse(expected);
        assertThat(optActual.isPresent()).isTrue();

        DoctorEntity actual = optActual.get();
        assertThat(actual.getUic()).isEqualTo(expected);
    }

    @Test
    public void testFindAllByDeletedFalse_ReturnsCollectionContainingDoctorsWhereDeletedIsFalse(){
        DoctorEntity doctor = new DoctorEntity();
        ReflectionTestUtils.setField(doctor, "uic", "0001");
        ReflectionTestUtils.setField(doctor, "name", "Georgiev");
        ReflectionTestUtils.setField(doctor, "birthDate", LocalDate.of(1990, 1, 1));
        ReflectionTestUtils.setField(doctor, "deleted", Boolean.FALSE);

        Set<DoctorEntity> expected = new LinkedHashSet<>();
        expected.add(doctor);

        Set<DoctorEntity> actual = doctorRepository.findAllByDeletedFalse();
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSoftDelete_UpdatesFieldDeletedToFalseOnDoctorWithUic() {
        doctorRepository.softDelete("0001");

        Optional<DoctorEntity> actual = doctorRepository.findByUic("0001");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().isDeleted()).isTrue();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testSoftCreate_UpdatesFieldDeletedToTrueOnDoctorWithUic() {
        doctorRepository.softCreate("0002");

        Optional<DoctorEntity> actual = doctorRepository.findByUic("0002");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().isDeleted()).isFalse();
    }

    @Test
    public void testFindAllDoctorsWhoHaveMadeAppointments_ReturnsCollectionContainingOnlyDoctorsWhoHaveMadeAppointments() {
        DoctorEntity doctor = new DoctorEntity();
        ReflectionTestUtils.setField(doctor, "uic", "0001");
        ReflectionTestUtils.setField(doctor, "name", "Georgiev");
        ReflectionTestUtils.setField(doctor, "birthDate", LocalDate.of(1990, 1, 1));
        ReflectionTestUtils.setField(doctor, "deleted", Boolean.FALSE);

        Set<DoctorEntity> expected = new LinkedHashSet<>();
        expected.add(doctor);

        Set<DoctorEntity> actual = doctorRepository.findAllDoctorsWhoHaveMadeAppointments();
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    public void testTotalIncomeFromInsured_ReturnsTotalIncomeOfDoctorWithUicFromAppointmentsWherePatientWasInsuredThen() {
        double expected = 10.0;
        double actual = doctorRepository.totalIncomeFromInsured("0001").doubleValue();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testTotalIncomeFromNotInsured_ReturnsTotalIncomeOfDoctorWithUicFromAppointmentsWherePatientWasNotInsuredThen() {
        double expected = 0.0;
        BigDecimal actual = doctorRepository.totalIncomeFromNotInsured("0001");
        if (actual != null) {
            assertThat(actual.doubleValue()).isEqualTo(expected);
        }
        assertThat(actual).isNull();
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