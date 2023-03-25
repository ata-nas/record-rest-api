package com.rewedigital.medicalrecord.service.impl.manager;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.service.GpService;
import com.rewedigital.medicalrecord.service.PatientManagerService;
import com.rewedigital.medicalrecord.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientManagerServiceImpl implements PatientManagerService {

    private final PatientService patientService;
    private final GpService gpService;


    @Override
    public PatientEntity findByUic(String uic) {
        return patientService.findByUic(uic);
    }

    @Override
    public PatientDTO findByUicToDTO(String uic) {
        return patientService.findByUicToDTO(uic);
    }

    @Override
    public List<PatientEntity> getAllPatients() {
        return patientService.getAllPatients();
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
                // FIXME refactor this to be done with @GpUicValidation if null then ok if not then see if in db
                // and get rid of this service as a whole.
        );
    }

    @Override
    public void deletePatientByUic(String uic) {
        patientService.deletePatientByUic(uic);
    }

}
