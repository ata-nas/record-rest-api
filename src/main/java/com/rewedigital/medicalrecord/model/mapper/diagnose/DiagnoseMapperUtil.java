package com.rewedigital.medicalrecord.model.mapper.diagnose;

import org.mapstruct.Named;

public class DiagnoseMapperUtil {

    @Named("toUpper")
    public static String toUpper(String s) {
        return s.toUpperCase();
    }


}
