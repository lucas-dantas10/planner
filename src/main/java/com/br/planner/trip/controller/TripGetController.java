package com.br.planner.trip.controller;

import com.br.planner.participant.service.ParticipantServiceInterface;
import com.br.planner.trip.dto.CreateTripDto;
import com.br.planner.trip.dto.ResponseTripDto;
import com.br.planner.trip.entity.Trip;
import com.br.planner.trip.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class TripGetController {

    private final TripRepository tripRepository;
    private final ParticipantServiceInterface participantService;

    public TripGetController(TripRepository tripRepository,
                             ParticipantServiceInterface participantService) {
        this.tripRepository = tripRepository;
        this.participantService = participantService;
    }

    @GetMapping("/trips/{tripId}")
    public ResponseEntity<String> createTrip(@RequestParam String tripId) {
        return ResponseEntity.ok(tripId);
    }
}
