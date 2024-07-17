package com.br.planner.participant.service;

import com.br.planner.participant.dto.ParticipantCreateResponse;
import com.br.planner.participant.dto.ParticipantRequestPayload;
import com.br.planner.participant.entity.Participant;
import com.br.planner.participant.repository.ParticipantRepository;
import com.br.planner.trip.entity.Trip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ParticipantService implements ParticipantServiceInterface {


    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public void registerParticipantsToTrip(List<String> participants, Trip trip) {
        log.info("Enviando email para os participants");

        List<Participant> newParticipants = participants
                .stream()
                .map(email -> new Participant(email, trip))
                .toList();
        this.participantRepository.saveAll(newParticipants);

        log.info("Emails enviados");
    }

    @Override
    public ParticipantCreateResponse registerParticipantToTrip(ParticipantRequestPayload payload, Trip trip) {
        Participant newParticipant = new Participant(payload.email(), payload.name(), trip);
        this.participantRepository.save(newParticipant);

        return new ParticipantCreateResponse(newParticipant.getId());
    }

    @Override
    public void triggerConfirmationEmailToParticipants(UUID tripId) {

    }

    @Override
    public void triggerConfirmationEmailToParticipant(String email) {

    }
}
