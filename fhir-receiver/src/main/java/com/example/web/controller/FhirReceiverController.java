package com.example.web.controller;

import com.example.web.service.FhirReceiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fhir")
public class FhirReceiverController {
    private final FhirReceiverService fhirReceiverService;

    @PostMapping("/receive")
    @ResponseStatus(HttpStatus.OK)
    public void receiveFhirBundle(@RequestBody String fhirBundle) {
        fhirReceiverService.receiveFhirBundle(fhirBundle);
    }
}
