package com.example.web.service.impl;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import com.example.web.service.FhirReceiverService;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FhirReceiverServiceImpl implements FhirReceiverService {
    private static final String TOPIC = "encounterTopic";
    private static IParser parser = FhirContext.forR4().newJsonParser();
    @Autowired
    private KafkaTemplate<String, Encounter> kafkaTemplate;

    private void process(Bundle fhirBundle) {
        //StringBuilder encounters = new StringBuilder();
        for (Bundle.BundleEntryComponent entry : fhirBundle.getEntry()) {
            Resource resource = entry.getResource();
            if (resource instanceof Encounter encounter && encounter.getStatus()
                    == Encounter.EncounterStatus.FINISHED) {
                encounter.setSubject(null);
                encounter.setLocation(null);
                encounter.setServiceProvider(null);
                //encounters.append(parser.encodeResourceToString(encounter));
                //encounters.append("\n");
                kafkaTemplate.send(TOPIC, encounter);
            }
        }
    }

    @Override
    public void receiveFhirBundle(String bundle) {
        process(parser.parseResource(Bundle.class, bundle));
    }
}
