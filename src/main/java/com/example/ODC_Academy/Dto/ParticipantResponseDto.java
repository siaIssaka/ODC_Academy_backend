package com.example.ODC_Academy.dto;

/**
 * Corps de réponse renvoyé au client. Ne contient volontairement pas le
 * mot de passe (même haché) : il n'a rien à faire dans une réponse API.
 */
public record ParticipantResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String mail
) {
}