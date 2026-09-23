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

import com.example.ODC_Academy.dto.FormateurRequestDTO;
import com.example.ODC_Academy.dto.FormateurResponseDTO;
import com.example.ODC_Academy.mapper.FormateurMapper;
import com.example.ODC_Academy.model.Formateur;
import com.example.ODC_Academy.service.FormateurService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/formateurs")
public class FormateurController {

    private final FormateurService formateurService;

    public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    @PostMapping
    public ResponseEntity<FormateurResponseDTO> createFormateur(@Valid @RequestBody FormateurRequestDTO dto) {
        Formateur formateurToSave = FormateurMapper.toEntity(dto);
        Formateur savedFormateur = formateurService.saveFormateur(formateurToSave);
        FormateurResponseDTO responseDto = FormateurMapper.toResponseDto(savedFormateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<FormateurResponseDTO>> getAllFormateurs() {
        List<FormateurResponseDTO> formateurs = formateurService.getFormateurs()
                .stream()
                .map(FormateurMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(formateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormateurResponseDTO> getFormateurById(@PathVariable("id") Long id) {
        Formateur formateur = formateurService.getFormateur(id);
        return ResponseEntity.ok(FormateurMapper.toResponseDto(formateur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormateurResponseDTO> updateFormateur(
            @PathVariable("id") Long id,
            @Valid @RequestBody FormateurRequestDTO dto) {
        Formateur formateurToUpdate = FormateurMapper.toEntity(dto);
        Formateur updatedFormateur = formateurService.updateFormateur(id, formateurToUpdate);
        return ResponseEntity.ok(FormateurMapper.toResponseDto(updatedFormateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormateurById(@PathVariable("id") Long id) {
        formateurService.deleteFormateur(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllFormateurs() {
        formateurService.deleteAllFormateurs();
        return ResponseEntity.noContent().build();
    }
}
