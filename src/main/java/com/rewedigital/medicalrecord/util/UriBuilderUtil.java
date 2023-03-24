package com.rewedigital.medicalrecord.util;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class UriBuilderUtil {

    private final UriComponentsBuilder uriComponentsBuilder;

    public URI diagnoseCreatedURI(String name) {
        return uriComponentsBuilder
                .path("/api")
                .path("/diagnoses")
                .path("/" + name.toLowerCase())
                .build().toUri();
    }

}
