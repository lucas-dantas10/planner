package com.br.planner.link.service;

import com.br.planner.link.dto.LinkData;
import com.br.planner.link.dto.LinkRequestPayload;
import com.br.planner.link.dto.LinkResponse;
import com.br.planner.trip.entity.Trip;

import java.util.List;
import java.util.UUID;

public interface LinkServiceInterface {
    LinkResponse registerLink(LinkRequestPayload payload, Trip trip);
    List<LinkData> getAllLinks(UUID tripId);
}
