package com.br.planner.activity.service;

import com.br.planner.activity.dto.ActivityData;
import com.br.planner.activity.dto.ActivityRequestPayload;
import com.br.planner.activity.dto.ActivityResponse;
import com.br.planner.activity.entity.Activity;
import com.br.planner.activity.repository.ActivityRepository;
import com.br.planner.trip.entity.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService implements ActivityServiceInterface {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ActivityResponse registerActivity(ActivityRequestPayload payload,
                                             Trip trip) {
        Activity activity = new Activity(payload.title(), payload.occurs_at(), trip);

        this.activityRepository.save(activity);

        return new ActivityResponse(activity.getId());
    }

    public List<ActivityData> getAllActivities(UUID tripId) {
        return this.activityRepository
                .findByTripId(tripId)
                .stream()
                .map(activity -> new ActivityData(activity.getId(), activity.getTitle(), activity.getOccursAt()))
                .toList();
    }
}
