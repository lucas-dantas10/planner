package com.br.planner.trip.controller;

import com.br.planner.trip.dto.TripRequestDto;
import com.br.planner.trip.entity.Trip;
import com.br.planner.trip.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripUpdateController {

    private final TripRepository tripRepository;

    public TripUpdateController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @PutMapping("/trips/{tripId}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID tripId, @RequestBody TripRequestDto dto) {
        Optional<Trip> trip =this.tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Trip rawTrip = trip.get();
        rawTrip.setDestination(dto.destination());
        rawTrip.setEndsAt(LocalDateTime.parse(dto.ends_at(), DateTimeFormatter.ISO_DATE_TIME));
        rawTrip.setStartsAt(LocalDateTime.parse(dto.starts_at(), DateTimeFormatter.ISO_DATE_TIME));
        this.tripRepository.save(rawTrip);

        return ResponseEntity.ok(rawTrip);
    }
}
