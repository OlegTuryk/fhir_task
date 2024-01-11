package com.test.olegt;

import org.hl7.fhir.r4.model.Encounter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaFhirListenerServiceImpl implements KafkaFhirListenerService {
    private static final String TOPIC = "encounterTopic";

    @KafkaListener(topics = TOPIC)
    @Override
    public void listen(Encounter encounter) {
        System.out.println(encounter.toString());
    }
}
