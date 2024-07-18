package com.br.planner.participant.service;

import com.br.planner.participant.dto.ParticipantCreateResponse;
import com.br.planner.participant.dto.ParticipantDto;
import com.br.planner.participant.dto.ParticipantRequestPayload;
import com.br.planner.trip.entity.Trip;

import java.util.List;
import java.util.UUID;

public interface ParticipantServiceInterface {

    void registerParticipantsToTrip(List<String> participants, Trip trip);
    ParticipantCreateResponse registerParticipantToTrip(ParticipantRequestPayload payload, Trip trip);
    void triggerConfirmationEmailToParticipants(UUID tripId);
    void triggerConfirmationEmailToParticipant(String email);
    List<ParticipantDto> getAllParticipantsFromTrip(UUID tripId);
}
