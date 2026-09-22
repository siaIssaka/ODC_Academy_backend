package com.example.ODC_Academy.mapper;

import com.example.ODC_Academy.dto.ParticipantRequestDto;
import com.example.ODC_Academy.dto.ParticipantResponseDto;
import com.example.ODC_Academy.model.Participant;

/**
 * Conversion manuelle Entity <-> DTO.
 * Volontairement simple (pas de MapStruct) pour rester lisible en formation.
 */
public final class ParticipantMapper {

    private ParticipantMapper() {
    }

    public static ParticipantResponseDto toResponseDto(Participant participant) {
        return new ParticipantResponseDTO(
                participant.getId(),
                participant.getFirstName(),
                participant.getLastName(),
                participant.getMail()
        );
    }

    public static Participant toEntity(ParticipantRequestDto dto) {
        Participant participant = new Participant();
        participant.setFirstName(dto.getFirstName());
        participant.setLastName(dto.getLastName());
        participant.setMail(dto.getMail());
        participant.setPassword(dto.getPassword());
        return participant;
    }
}