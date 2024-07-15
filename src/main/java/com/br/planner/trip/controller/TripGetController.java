package com.br.planner.trip.controller;

import com.br.planner.participant.service.ParticipantServiceInterface;
import com.br.planner.trip.entity.Trip;
import com.br.planner.trip.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripGetController {

    private final TripRepository tripRepository;
    private final ParticipantServiceInterface participantService;

    public TripGetController(TripRepository tripRepository,
                             ParticipantServiceInterface participantService) {
        this.tripRepository = tripRepository;
        this.participantService = participantService;
    }

    @GetMapping("/trips/{tripId}")
    public ResponseEntity<Trip> getTripDatails(@PathVariable UUID tripId) {
        Optional<Trip> trip = this.tripRepository.findById(tripId);

        return trip.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
