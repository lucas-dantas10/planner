package com.br.planner.trip.controller;

import com.br.planner.participant.dto.ParticipantDto;
import com.br.planner.participant.entity.Participant;
import com.br.planner.participant.repository.ParticipantRepository;
import com.br.planner.participant.service.ParticipantServiceInterface;
import com.br.planner.trip.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripParticipantsController {

    private final ParticipantServiceInterface participantService;

    public TripParticipantsController(ParticipantServiceInterface participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/trips/{tripId}/participants")
    public ResponseEntity<List<ParticipantDto>> getParticipantsOfTrip(@PathVariable UUID tripId) {
        List<ParticipantDto> participants = this.participantService
                .getAllParticipantsFromTrip(tripId);

        if (participants.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(participants);
    }
}
