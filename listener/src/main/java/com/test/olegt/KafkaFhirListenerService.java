package com.test.olegt;

import org.hl7.fhir.r4.model.Encounter;

public interface KafkaFhirListenerService {
    public void listen(Encounter encounter);
}
