package com.br.planner.trip.controller;

import com.br.planner.participant.service.ParticipantServiceInterface;
import com.br.planner.trip.dto.CreateTripDto;
import com.br.planner.trip.dto.ResponseTripDto;
import com.br.planner.trip.entity.Trip;
import com.br.planner.trip.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TripCreateController {

    private final TripRepository tripRepository;
    private final ParticipantServiceInterface participantService;

    public TripCreateController(TripRepository tripRepository, ParticipantServiceInterface participantService) {
        this.tripRepository = tripRepository;
        this.participantService = participantService;
    }

    @PostMapping("/trips")
    public ResponseEntity<ResponseTripDto> createTrip(@RequestBody CreateTripDto dto) {
        Trip newTrip = new Trip(dto);

        this.tripRepository.save(newTrip);

        this.participantService.registerPartipantsToTrip(dto.emails_to_invite(), newTrip.getId());

        return ResponseEntity.ok(new ResponseTripDto(newTrip.getId()));
    }
}
