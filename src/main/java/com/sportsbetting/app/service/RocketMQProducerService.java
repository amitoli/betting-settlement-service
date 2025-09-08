package com.sportsbetting.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbetting.app.model.BetSettlement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketMQProducerService {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQProducerService.class);

    private final ObjectMapper objectMapper;

    private final RocketMQConsumerService rocketMQConsumerService;

    @Autowired
    public RocketMQProducerService(RocketMQConsumerService rocketMQConsumerService, ObjectMapper objectMapper) {
        this.rocketMQConsumerService = rocketMQConsumerService;
        this.objectMapper = objectMapper;
    }

    public void sendBetSettlement(BetSettlement betSettlement) {
        try {
            String message = objectMapper.writeValueAsString(betSettlement);
            logger.info("MOCK ROCKETMQ PRODUCER - Sending bet settlement: {}", message);

            // Simulate sending to RocketMQ by directly calling the consumer
            rocketMQConsumerService.processBetSettlement(message);

        } catch (Exception e) {
            logger.error("Error sending bet settlement to RocketMQ", e);
        }
    }
}
