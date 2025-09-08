package com.sportsbetting.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class BetSettlement {
    @JsonProperty("betId")
    private Long betId;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("betAmount")
    private BigDecimal betAmount;

    @JsonProperty("isWinning")
    private boolean isWinning;

    public BetSettlement() {}

    public BetSettlement(Long betId, String userId, String eventId, BigDecimal betAmount, boolean isWinning) {
        this.betId = betId;
        this.userId = userId;
        this.eventId = eventId;
        this.betAmount = betAmount;
        this.isWinning = isWinning;
    }

    public Long getBetId() { return betId; }
    public void setBetId(Long betId) { this.betId = betId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public BigDecimal getBetAmount() { return betAmount; }
    public void setBetAmount(BigDecimal betAmount) { this.betAmount = betAmount; }

    public boolean isWinning() { return isWinning; }
    public void setWinning(boolean winning) { isWinning = winning; }
}

