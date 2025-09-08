package com.sportsbetting.app.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long betId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(name = "event_market_id", nullable = false)
    private String eventMarketId;

    @Column(name = "event_winner_id", nullable = false)
    private String eventWinnerId;

    @Column(name = "bet_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal betAmount;

    @Column(name = "settled", nullable = false)
    private boolean settled = false;

    public Bet() {}

    public Bet(String userId, String eventId, String eventMarketId, String eventWinnerId, BigDecimal betAmount) {
        this.userId = userId;
        this.eventId = eventId;
        this.eventMarketId = eventMarketId;
        this.eventWinnerId = eventWinnerId;
        this.betAmount = betAmount;
    }

    public Long getBetId() { return betId; }
    public void setBetId(Long betId) { this.betId = betId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getEventMarketId() { return eventMarketId; }
    public void setEventMarketId(String eventMarketId) { this.eventMarketId = eventMarketId; }

    public String getEventWinnerId() { return eventWinnerId; }
    public void setEventWinnerId(String eventWinnerId) { this.eventWinnerId = eventWinnerId; }

    public BigDecimal getBetAmount() { return betAmount; }
    public void setBetAmount(BigDecimal betAmount) { this.betAmount = betAmount; }

    public boolean isSettled() { return settled; }
    public void setSettled(boolean settled) { this.settled = settled; }
}
