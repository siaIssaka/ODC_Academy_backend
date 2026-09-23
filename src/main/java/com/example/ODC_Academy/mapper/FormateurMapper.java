package com.example.ODC_Academy.mapper;

import com.example.ODC_Academy.dto.FormateurRequestDTO;
import com.example.ODC_Academy.dto.FormateurResponseDTO;
import com.example.ODC_Academy.model.Formateur;

/**
 * Conversion manuelle Entity <-> DTO.
 * Volontairement simple (pas de MapStruct) pour rester lisible en formation.
 */
public final class FormateurMapper {

    private FormateurMapper() {
    }

    public static FormateurResponseDTO toResponseDto(Formateur formateur) {
        return new FormateurResponseDTO(
                formateur.getId(),
                formateur.getFirstName(),
                formateur.getLastName(),
                formateur.getMail()
        );
    }

    public static Formateur toEntity(FormateurRequestDTO dto) {
        Formateur formateur = new Formateur();
        formateur.setFirstName(dto.getFirstName());
        formateur.setLastName(dto.getLastName());
        formateur.setMail(dto.getMail());
        formateur.setPassword(dto.getPassword());
        return formateur;
    }
}