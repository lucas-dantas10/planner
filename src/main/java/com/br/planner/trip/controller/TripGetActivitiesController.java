package com.br.planner.trip.controller;

import com.br.planner.activity.dto.ActivityData;
import com.br.planner.activity.service.ActivityServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TripGetActivitiesController {

    private final ActivityServiceInterface activityService;

    public TripGetActivitiesController(ActivityServiceInterface activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/trips/{tripId}/activities")
    public ResponseEntity<List<ActivityData>> getAllActivities(@PathVariable UUID tripId) {
        List<ActivityData> activities = this.activityService.getAllActivities(tripId);

        if (activities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(activities);
    }
}
