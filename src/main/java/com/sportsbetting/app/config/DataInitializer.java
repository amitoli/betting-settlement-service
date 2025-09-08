package com.sportsbetting.app.config;

import com.sportsbetting.app.model.Bet;
import com.sportsbetting.app.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BetRepository betRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize some sample bets
        betRepository.save(new Bet("user1", "event1", "market1", "team1", new BigDecimal("100.00")));
        betRepository.save(new Bet("user2", "event1", "market1", "team2", new BigDecimal("50.00")));
        betRepository.save(new Bet("user3", "event2", "market2", "team3", new BigDecimal("75.00")));
        betRepository.save(new Bet("user4", "event1", "market1", "team1", new BigDecimal("200.00")));
    }
}
