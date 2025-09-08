package com.sportsbetting.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbetting.app.model.Bet;
import com.sportsbetting.app.model.BetSettlement;
import com.sportsbetting.app.model.EventOutcome;
import com.sportsbetting.app.repository.BetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BetSettlementService {

    private static final Logger logger = LoggerFactory.getLogger(BetSettlementService.class);

    private final BetRepository betRepository;

    private final RocketMQProducerService rocketMQProducerService;

    private final ObjectMapper objectMapper;

    @Autowired
    public BetSettlementService(BetRepository betRepository,
                                RocketMQProducerService rocketMQProducerService,
                                ObjectMapper objectMapper) {
        this.betRepository = betRepository;
        this.rocketMQProducerService = rocketMQProducerService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "event-outcomes", groupId = "bet-settlement-group")
    public void handleEventOutcome(String message) {
        try {
            logger.info("Received event outcome from Kafka: {}", message);

            EventOutcome eventOutcome = objectMapper.readValue(message, EventOutcome.class);
            List<Bet> betsToSettle = betRepository.findByEventIdAndSettledFalse(eventOutcome.getEventId());

            logger.info("Found {} bets to settle for event {}", betsToSettle.size(), eventOutcome.getEventId());

            for (Bet bet : betsToSettle) {
                boolean isWinning = bet.getEventWinnerId().equals(eventOutcome.getEventWinnerId());

                BetSettlement settlement = new BetSettlement(
                        bet.getBetId(),
                        bet.getUserId(),
                        bet.getEventId(),
                        bet.getBetAmount(),
                        isWinning
                );

                rocketMQProducerService.sendBetSettlement(settlement);
            }

        } catch (Exception e) {
            logger.error("Error processing event outcome", e);
        }
    }
}
