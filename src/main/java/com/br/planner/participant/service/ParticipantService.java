package com.br.planner.participant.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService implements ParticipantServiceInterface {
    @Override
    public void registerPartipantsToTrip(List<String> participants, UUID tripId) {

    }

    @Override
    public void triggerConfirmationEmailToParticipants(UUID tripId) {

    }
}
