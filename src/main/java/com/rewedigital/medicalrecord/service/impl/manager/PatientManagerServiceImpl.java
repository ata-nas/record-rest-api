package com.rewedigital.medicalrecord.service.impl.manager;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PercentNotInsuredPatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.service.GpService;
import com.rewedigital.medicalrecord.service.PatientManagerService;
import com.rewedigital.medicalrecord.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientManagerServiceImpl implements PatientManagerService {

    private final PatientService patientService;
    private final GpService gpService;


    @Override
    public PatientEntity findByUic(String uic) {
        return patientService.getPatientByUic(uic);
    }

    @Override
    public PatientDTO findByUicToDTO(String uic) {
        return patientService.getPatientByUicToDTO(uic);
    }

    @Override
    public List<PatientEntity> getAllPatients() {
        return patientService.getAllPatients();
    }

    @Override
    public List<PatientEntity> getAllPatientsInsuredFalse() {
        return patientService.getAllPatientsInsuredFalse();
    }

    @Override
    public List<PatientDTO> getAllPatientsInsuredFalseToDTO() {
        return patientService.getAllPatientsInsuredFalseToDTO();
    }

    @Override
    public List<PatientEntity> getAllPatientsInsuredTrue() {
        return patientService.getAllPatientsInsuredTrue();
    }

    @Override
    public List<PatientDTO> getAllPatientsInsuredTrueToDTO() {
        return patientService.getAllPatientsInsuredTrueToDTO();
    }

    @Override
    public List<PatientDTO> getAllPatientsToDTO() {
        return patientService.getAllPatientsToDTO();
    }

    @Override
    public PatientDTO createPatient(CreatePatientDTO createPatientDTO) {
        return patientService.createPatient(createPatientDTO);
    }

    @Override
    public PatientDTO updatePatient(String uic, UpdatePatientDTO updatePatientDTO) {
        return patientService.updatePatient(
                findByUic(uic)
                        .setName(updatePatientDTO.getName())
                        .setInsured(updatePatientDTO.getInsured())
                        .setGp(updatePatientDTO.getGpUic() != null ?
                                gpService.findByUic(updatePatientDTO.getGpUic()) : null)
        );
    }

    @Override
    public void deletePatientByUic(String uic) {
        patientService.deletePatientByUic(uic);
    }

    @Override
    public PercentNotInsuredPatientDTO totalPercentNotInsuredPatients() {
        return new PercentNotInsuredPatientDTO().setPercentNotInsured(calculatePercentageNotInsured());
    }

    private BigDecimal calculatePercentageNotInsured() {
        int totalPeople = patientService.getAllPatients().size();
        int targetPeople = patientService.countDistinctByInsuredFalse();
        return BigDecimal.valueOf(targetPeople)
                .multiply(BigDecimal.valueOf(100.00)
                        .setScale(4, RoundingMode.HALF_EVEN)
                        .divide(BigDecimal.valueOf(totalPeople), RoundingMode.HALF_EVEN));
    }

    @Override
    public PercentNotInsuredPatientDTO totalPercentInsuredPatients() {
        return new PercentNotInsuredPatientDTO().setPercentNotInsured(calculatePercentageInsured());
    }

    private BigDecimal calculatePercentageInsured() {
        int totalPeople = patientService.getAllPatients().size();
        int targetPeople = patientService.countDistinctByInsuredTrue();
        return BigDecimal.valueOf(targetPeople)
                .multiply(BigDecimal.valueOf(100.00)
                        .setScale(4, RoundingMode.HALF_EVEN)
                        .divide(BigDecimal.valueOf(totalPeople), RoundingMode.HALF_EVEN));
    }

}
