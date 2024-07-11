package com.br.planner.participant.service;

import java.util.List;
import java.util.UUID;

public interface ParticipantServiceInterface {

    void registerPartipantsToTrip(List<String> participants, UUID tripId);
    void triggerConfirmationEmailToParticipants(UUID tripId);
}
