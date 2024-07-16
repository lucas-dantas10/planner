package com.br.planner.participant.controller;

import com.br.planner.participant.dto.ParticipantRequestPayload;
import com.br.planner.participant.entity.Participant;
import com.br.planner.participant.repository.ParticipantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantConfirmController {

    private final ParticipantRepository participantRepository;

    public ParticipantConfirmController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @PostMapping("/{participantId}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID participantId,
                                                          @RequestBody ParticipantRequestPayload payload) {
        Optional<Participant> participant = this.participantRepository.findById(participantId);

        if (participant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Participant rawParticipant = participant.get();
        rawParticipant.setIsConfirmed(true);
        rawParticipant.setName(payload.name());
        rawParticipant.setEmail(payload.email());

        this.participantRepository.save(rawParticipant);

        return ResponseEntity.ok(rawParticipant);
    }
}
