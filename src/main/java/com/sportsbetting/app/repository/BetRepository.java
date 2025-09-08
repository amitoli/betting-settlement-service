package com.sportsbetting.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.sportsbetting.app.model.Bet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByEventIdAndSettledFalse(String eventId);
}
