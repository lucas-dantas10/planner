package com.br.planner.activity.service;

import com.br.planner.activity.dto.ActivityRequestPayload;
import com.br.planner.activity.dto.ActivityResponse;
import com.br.planner.activity.entity.Activity;
import com.br.planner.activity.repository.ActivityRepository;
import com.br.planner.trip.entity.Trip;
import org.springframework.stereotype.Service;

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
}
