package com.example.ODC_Academy.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ODC_Academy.dto.AdministrateurRequestDTO;
import com.example.ODC_Academy.dto.AdministrateurResponseDTO;
import com.example.ODC_Academy.mapper.AdministrateurMapper;
import com.example.ODC_Academy.model.Administrateur;
import com.example.ODC_Academy.service.AdministrateurService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/administrateurs")
public class AdministrateurController {

    private final AdministrateurService administrateurService;

    public AdministrateurController(AdministrateurService administrateurService) {
        this.administrateurService = administrateurService;
    }

    @PostMapping
    public ResponseEntity<AdministrateurResponseDTO> createAdministrateur(@Valid @RequestBody AdministrateurRequestDTO dto) {
        Administrateur administrateurToSave = AdministrateurMapper.toEntity(dto);
        Administrateur savedAdministrateur = administrateurService.saveAdministrateur(administrateurToSave);
        AdministrateurResponseDTO responseDto = AdministrateurMapper.toResponseDto(savedAdministrateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AdministrateurResponseDTO>> getAllAdministrateurs() {
        List<AdministrateurResponseDTO> administrateurs = administrateurService.getAdministrateurs()
                .stream()
                .map(AdministrateurMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(administrateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrateurResponseDTO> getAdministrateurById(@PathVariable("id") Long id) {
        Administrateur administrateur = administrateurService.getAdministrateur(id);
        return ResponseEntity.ok(AdministrateurMapper.toResponseDto(administrateur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrateurResponseDTO> updateAdministrateur(
            @PathVariable("id") Long id,
            @Valid @RequestBody AdministrateurRequestDTO dto) {
        Administrateur administrateurToUpdate = AdministrateurMapper.toEntity(dto);
        Administrateur updatedAdministrateur = administrateurService.updateAdministrateur(id, administrateurToUpdate);
        return ResponseEntity.ok(AdministrateurMapper.toResponseDto(updatedAdministrateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateurById(@PathVariable("id") Long id) {
        administrateurService.deleteAdministrateur(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllAdministrateurs() {
        administrateurService.deleteAllAdministrateurs();
        return ResponseEntity.noContent().build();
    }
}
