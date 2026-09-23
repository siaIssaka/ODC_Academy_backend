package com.example.ODC_Academy.mapper;

import com.example.ODC_Academy.dto.AdministrateurRequestDTO;
import com.example.ODC_Academy.dto.AdministrateurResponseDTO;
import com.example.ODC_Academy.model.Administrateur;

public final class AdministrateurMapper {

    private AdministrateurMapper() {
    }

    public static AdministrateurResponseDTO toResponseDto(Administrateur administrateur) {
        return new AdministrateurResponseDTO(
                administrateur.getId(),
                administrateur.getFirstName(),
                administrateur.getLastName(),
                administrateur.getMail()
        );
    }

    public static Administrateur toEntity(AdministrateurRequestDTO dto) {
        Administrateur administrateur = new Administrateur();
        administrateur.setFirstName(dto.getFirstName());
        administrateur.setLastName(dto.getLastName());
        administrateur.setMail(dto.getMail());
        administrateur.setPassword(dto.getPassword());
        return administrateur;
    }
}
