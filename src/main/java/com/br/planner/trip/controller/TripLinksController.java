package com.br.planner.trip.controller;

import com.br.planner.link.dto.LinkRequestPayload;
import com.br.planner.link.dto.LinkResponse;
import com.br.planner.link.service.LinkServiceInterface;
import com.br.planner.trip.entity.Trip;
import com.br.planner.trip.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripLinksController {

    private final LinkServiceInterface linkService;
    private final TripRepository tripRepository;

    public TripLinksController(LinkServiceInterface linkService, TripRepository tripRepository) {
        this.linkService = linkService;
        this.tripRepository = tripRepository;
    }

    @PostMapping("/trips/{tripId}/links")
    public ResponseEntity<LinkResponse> createLink(@PathVariable UUID tripId,
                                                   @RequestBody LinkRequestPayload payload) {
        Optional<Trip> trip = this.tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LinkResponse response = this.linkService.registerLink(payload, trip.get());

        return ResponseEntity.ok(response);
    }
}
