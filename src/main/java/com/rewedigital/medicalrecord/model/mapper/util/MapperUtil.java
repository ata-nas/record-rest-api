package com.rewedigital.medicalrecord.model.mapper.util;

import org.mapstruct.Named;

public class MapperUtil {

    @Named("toUpper")
    public static String toUpper(String s) {
        return s.toUpperCase();
    }

}
