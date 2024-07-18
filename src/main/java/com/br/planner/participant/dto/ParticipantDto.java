package com.br.planner.participant.dto;

import java.util.UUID;

public record ParticipantDto(UUID id, String name, String email, Boolean isConfirmed) {
}
