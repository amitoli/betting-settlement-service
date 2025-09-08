package com.sportsbetting.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbetting.app.model.EventOutcome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventOutcomeService {

    private static final Logger logger = LoggerFactory.getLogger(EventOutcomeService.class);
    private static final String TOPIC = "event-outcomes";

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public EventOutcomeService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishEventOutcome(EventOutcome eventOutcome) {
        try {
            String message = objectMapper.writeValueAsString(eventOutcome);
            kafkaTemplate.send(TOPIC, eventOutcome.getEventId(), message);
            logger.info("Published event outcome to Kafka: {}", message);
        } catch (Exception e) {
            logger.error("Error publishing event outcome to Kafka", e);
            throw new RuntimeException("Failed to publish event outcome", e);
        }
    }
}
