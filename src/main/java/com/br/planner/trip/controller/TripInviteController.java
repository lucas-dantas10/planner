package com.br.planner.trip.controller;

import com.br.planner.participant.dto.ParticipantCreateResponse;
import com.br.planner.participant.dto.ParticipantRequestPayload;
import com.br.planner.participant.service.ParticipantServiceInterface;
import com.br.planner.trip.entity.Trip;
import com.br.planner.trip.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripInviteController {

    private final TripRepository tripRepository;
    private final ParticipantServiceInterface participantService;

    public TripInviteController(TripRepository tripRepository, ParticipantServiceInterface participantService) {
        this.tripRepository = tripRepository;
        this.participantService = participantService;
    }

    @PostMapping("/trips/{tripId}/invite")
    public ResponseEntity<ParticipantCreateResponse> sendInvite(@PathVariable UUID tripId,
                                                                @RequestBody ParticipantRequestPayload payload) {
        Optional<Trip> trip = this.tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Trip rawTrip = trip.get();

        ParticipantCreateResponse participant = this.participantService
                .registerParticipantToTrip(payload, rawTrip);

        if (rawTrip.getIsConfirmed()) this.participantService.triggerConfirmationEmailToParticipant(payload.email());

        return ResponseEntity.ok(participant);
    }
}
