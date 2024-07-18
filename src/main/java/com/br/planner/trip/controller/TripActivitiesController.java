package com.br.planner.trip.controller;

import com.br.planner.activity.dto.ActivityRequestPayload;
import com.br.planner.activity.dto.ActivityResponse;
import com.br.planner.activity.service.ActivityService;
import com.br.planner.activity.service.ActivityServiceInterface;
import com.br.planner.trip.entity.Trip;
import com.br.planner.trip.repository.TripRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripActivitiesController {

    private final TripRepository tripRepository;

    private final ActivityServiceInterface activityService;

    public TripActivitiesController(TripRepository tripRepository,
                                    ActivityServiceInterface activityService) {
        this.tripRepository = tripRepository;
        this.activityService = activityService;
    }

    @PostMapping("/trips/{tripId}/activities")
    public ResponseEntity<ActivityResponse> createActivity(@PathVariable UUID tripId,
                                                           @RequestBody ActivityRequestPayload payload) {
        Optional<Trip> trip = this.tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ActivityResponse ActivityService = this.activityService.registerActivity(payload, trip.get());

        return ResponseEntity.ok(ActivityService);
    }
}
