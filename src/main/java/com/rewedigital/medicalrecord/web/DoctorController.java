package com.rewedigital.medicalrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/healthcare/bulgaria/doctors")
@RequiredArgsConstructor
public class DoctorController {

    // post mapping /doctors/gp/{uic} will make the existing doctor (if exist) into gp
    // delete mapping /doctors/gp/{uic} will remove the existing gp entry from gp table (if exist)
    // I will control all doctor and gp from here, they are connected through inheritance I will use path variable
    // to differentiate which I want to access. I will have a Doctor manager orchestration to handle operations.

}
