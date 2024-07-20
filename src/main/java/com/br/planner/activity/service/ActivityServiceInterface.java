package com.br.planner.activity.service;

import com.br.planner.activity.dto.ActivityData;
import com.br.planner.activity.dto.ActivityRequestPayload;
import com.br.planner.activity.dto.ActivityResponse;
import com.br.planner.trip.entity.Trip;

import java.util.List;
import java.util.UUID;

public interface ActivityServiceInterface {
    ActivityResponse registerActivity(ActivityRequestPayload payload,
                                             Trip trip);
    List<ActivityData> getAllActivities(UUID tripId);
}
