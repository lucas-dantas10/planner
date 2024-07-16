package com.br.planner.trip.controller;

import com.br.planner.participant.service.ParticipantServiceInterface;
import com.br.planner.trip.entity.Trip;
import com.br.planner.trip.repository.TripRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripConfirmController {

    private final TripRepository tripRepository;
    private final ParticipantServiceInterface participantService;

    public TripConfirmController(TripRepository tripRepository,
                                 ParticipantServiceInterface participantService) {
        this.tripRepository = tripRepository;
        this.participantService = participantService;
    }

    @GetMapping("/trips/{tripId}/confirm")
    public ResponseEntity<Trip> confrmTrip(@PathVariable UUID tripId) {
        Optional<Trip> trip = this.tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Trip rawTrip = trip.get();
        rawTrip.setIsConfirmed(true);
        this.participantService.triggerConfirmationEmailToParticipants(tripId);

        this.tripRepository.save(rawTrip);

        return ResponseEntity.ok(rawTrip);
    }
}
