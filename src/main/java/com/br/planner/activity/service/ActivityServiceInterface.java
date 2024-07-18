package com.br.planner.activity.service;

import com.br.planner.activity.dto.ActivityRequestPayload;
import com.br.planner.activity.dto.ActivityResponse;
import com.br.planner.trip.entity.Trip;

public interface ActivityServiceInterface {
    ActivityResponse registerActivity(ActivityRequestPayload payload,
                                             Trip trip);
}
