package com.sportsbetting.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsbetting.app.model.Bet;
import com.sportsbetting.app.model.BetSettlement;
import com.sportsbetting.app.repository.BetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketMQConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQConsumerService.class);

    private final BetRepository betRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public RocketMQConsumerService(BetRepository betRepository, ObjectMapper objectMapper) {
        this.betRepository = betRepository;
        this.objectMapper = objectMapper;
    }

    public void processBetSettlement(String message) {
        try {
            logger.info("MOCK ROCKETMQ CONSUMER - Processing bet settlement: {}", message);

            BetSettlement settlement = objectMapper.readValue(message, BetSettlement.class);

            // Update bet as settled
            Bet bet = betRepository.findById(settlement.getBetId()).orElse(null);
            if (bet != null) {
                bet.setSettled(true);
                betRepository.save(bet);

                logger.info("Bet {} settled successfully. Winning: {}, Amount: {}",
                        settlement.getBetId(), settlement.isWinning(), settlement.getBetAmount());
            }

        } catch (Exception e) {
            logger.error("Error processing bet settlement", e);
        }
    }
}
