package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchAppointmentEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.appointment.AppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.CreateAppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.UpdateAppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.stats.TotalIncomeDTO;
import com.rewedigital.medicalrecord.model.entity.AppointmentEntity;
import com.rewedigital.medicalrecord.model.mapper.AppointmentMapperImpl;
import com.rewedigital.medicalrecord.repository.AppointmentRepository;
import com.rewedigital.medicalrecord.service.impl.AppointmentServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Spy
    private AppointmentMapperImpl appointmentMapper;

    @InjectMocks
    private AppointmentServiceImpl toTest;

    @BeforeEach
    public void init() {
        openMocks(appointmentRepository);
        openMocks(appointmentMapper);
    }

    @Test
    public void testGetByUic_ReturnsAppointmentByUic() {
        AppointmentEntity expected = new AppointmentEntity();
        ReflectionTestUtils.setField(expected,"uic", "1");

        when(appointmentRepository.findByUic("1"))
                .thenReturn(Optional.of(expected));


        AppointmentEntity actual = toTest.getByUic("1");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetByUic_ThrowsNoSuchAppointmentEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchAppointmentEntityFoundException.class)
                .isThrownBy(() -> toTest.getByUic("wrong value"));
    }

    @Test
    public void testGetByUicToDTO_ReturnsAppointmentEntity() {
        AppointmentEntity expected = new AppointmentEntity();
        ReflectionTestUtils.setField(expected,"uic", "1");

        when(appointmentRepository.findByUic("1"))
                .thenReturn(Optional.of(expected));

        AppointmentDTO actual = toTest.getByUicToDTO("1");

        assertThat(actual.getUic()).isEqualTo(expected.getUic());
    }

    @Test
    public void testGetByUicToDTO_ThrowsNoSuchAppointmentEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchAppointmentEntityFoundException.class)
                .isThrownBy(() -> toTest.getByUicToDTO("wrong value"));
    }

    @Test
    public void testGetAll_ReturnsCollectionWithAllAppointments() {
        when(appointmentRepository.findAll())
                .thenReturn(List.of(new AppointmentEntity()));

        List<AppointmentEntity> actual = toTest.getAll();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.get(0)).isExactlyInstanceOf(AppointmentEntity.class);
    }

    @Test
    public void testGetAll_ThrowsNoSuchAppointmentEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchAppointmentEntityFoundException.class)
                .isThrownBy(() -> toTest.getAll());
    }

    @Test
    public void testGetAllToDTO_ReturnsCollectionWithAllAppointmentsDTO() {
        when(appointmentRepository.findAll())
                .thenReturn(List.of(new AppointmentEntity()));

        List<AppointmentDTO> actual = toTest.getAllToDTO();

        assertThatCollection(actual).isNotNull();
        assertThatCollection(actual).isNotEmpty();
        assertThatCollection(actual).doesNotContainNull();
        assertThatCollection(actual).hasSize(1);
        assertThat(actual.get(0)).isExactlyInstanceOf(AppointmentDTO.class);
    }

    @Test
    public void testGetAllToDTO_ThrowsNoSuchAppointmentEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchAppointmentEntityFoundException.class)
                .isThrownBy(() -> toTest.getAllToDTO());
    }

    @Test
    public void testCreate_ReturnsAppointmentDTOOfCreatedAppointment() {
        CreateAppointmentDTO createAppointmentDTO = new CreateAppointmentDTO();
        ReflectionTestUtils.setField(createAppointmentDTO,"uic", "1");

        AppointmentEntity appointmentEntity = new AppointmentEntity();
        ReflectionTestUtils.setField(appointmentEntity,"uic", "1");

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        ReflectionTestUtils.setField(appointmentDTO,"uic", "1");

        when(appointmentMapper.toEntity(any(CreateAppointmentDTO.class)))
                .thenReturn(appointmentEntity);

        when(appointmentRepository.save(any(AppointmentEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        when(appointmentMapper.toDTO(any(AppointmentEntity.class)))
                .thenReturn(appointmentDTO);

        AppointmentDTO actual = toTest.create(createAppointmentDTO);

        assertThat(actual.getUic()).isEqualTo("1");
        assertThat(actual).isExactlyInstanceOf(AppointmentDTO.class);
    }

    @Test
    public void testUpdate_ReturnsAppointmentDTOOfUpdatedAppointment() {
        UpdateAppointmentDTO updateAppointmentDTO = new UpdateAppointmentDTO();
        ReflectionTestUtils.setField(updateAppointmentDTO, "description", "test");

        AppointmentEntity appointmentEntity = new AppointmentEntity();
        ReflectionTestUtils.setField(appointmentEntity,"description", "test");

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        ReflectionTestUtils.setField(appointmentDTO,"description", "test");

        when(appointmentMapper.toEntity(any(UpdateAppointmentDTO.class), any(AppointmentEntity.class)))
                .thenReturn(appointmentEntity);

        when(appointmentRepository.save(any(AppointmentEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        when(appointmentMapper.toDTO(any(AppointmentEntity.class)))
                .thenReturn(appointmentDTO);

        when(appointmentRepository.findByUic("1"))
                .thenReturn(Optional.of(appointmentEntity));

        AppointmentDTO actual = toTest.update("1", updateAppointmentDTO);

        assertThat(actual.getDescription()).isEqualTo("test");
        assertThat(actual).isExactlyInstanceOf(AppointmentDTO.class);
    }

    @Test
    public void testUpdate_ThrowsNoSuchAppointmentEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchAppointmentEntityFoundException.class)
                .isThrownBy(() -> toTest.update("wrong value", new UpdateAppointmentDTO()));
    }

    @Test
    public void testDelete_ThrowsNoSuchAppointmentEntityFoundExceptionIfEntityNotFound() {
        assertThatExceptionOfType(NoSuchAppointmentEntityFoundException.class)
                .isThrownBy(() -> toTest.delete("wrong value"));
    }

    @Test
    public void testGetTotalIncome_ReturnsTotalIncomeDTOWithTotalAmount() {
        TotalIncomeDTO totalIncomeDTO = new TotalIncomeDTO();
        ReflectionTestUtils.setField(totalIncomeDTO, "totalIncome", BigDecimal.TEN);

        when(appointmentRepository.getTotalIncome())
                .thenReturn(BigDecimal.TEN);

        when(appointmentMapper.toDTO(any(BigDecimal.class)))
                .thenReturn(totalIncomeDTO);

        TotalIncomeDTO actual = toTest.getTotalIncome();

        assertThat(actual.getTotalIncome().doubleValue()).isEqualTo(BigDecimal.TEN.doubleValue());
        assertThat(actual).isExactlyInstanceOf(TotalIncomeDTO.class);
    }

}
