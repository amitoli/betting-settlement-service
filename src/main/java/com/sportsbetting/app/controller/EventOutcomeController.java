package com.sportsbetting.app.controller;

import com.sportsbetting.app.model.EventOutcome;
import com.sportsbetting.app.service.EventOutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-outcomes")
public class EventOutcomeController {

    private final EventOutcomeService eventOutcomeService;

    @Autowired
    public EventOutcomeController(EventOutcomeService eventOutcomeService) {
        this.eventOutcomeService = eventOutcomeService;
    }

    @PostMapping
    public ResponseEntity<String> publishEventOutcome(@RequestBody EventOutcome eventOutcome) {
        try {
            eventOutcomeService.publishEventOutcome(eventOutcome);
            return ResponseEntity.ok("Event outcome published successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error publishing event outcome: " + e.getMessage());
        }
    }
}
