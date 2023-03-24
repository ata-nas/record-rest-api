package com.rewedigital.medicalrecord.model.dto.exception.bind;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BindExceptionPropertiesDTO {

private final String field;
    private final String defaultMessage;

}
