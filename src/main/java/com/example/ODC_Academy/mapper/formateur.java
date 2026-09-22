package com.example.ODC_Academy.mapper;

import com.example.ODC_Academy.dto.FormateurRequestDto;
import com.example.ODC_Academy.dto.FormateurResponseDto;
import com.example.ODC_Academy.model.Formateur;

/**
 * Conversion manuelle Entity <-> DTO.
 * Volontairement simple (pas de MapStruct) pour rester lisible en formation.
 */
public final class FormateurMapper {

    private FormateurMapper() {
    }

    public static FormateurResponseDto toResponseDto(Formateur formateur) {
        return new FormateurResponseDto(
                formateur.getId(),
                formateur.getFirstName(),
                formateur.getLastName(),
                formateur.getMail()
        );
    }

    public static Formateur toEntity(FormateurRequestDto dto) {
        Formateur formateur = new Formateur();
        formateur.setFirstName(dto.getFirstName());
        formateur.setLastName(dto.getLastName());
        formateur.setMail(dto.getMail());
        formateur.setPassword(dto.getPassword());
        return formateur;
    }
}