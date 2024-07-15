package com.br.planner.trip.dto;

import java.util.List;

public record TripRequestDto(String destination,
                             String starts_at,
                             String ends_at,
                             List<String> emails_to_invite,
                             String owner_name,
                             String owner_email) {
}
