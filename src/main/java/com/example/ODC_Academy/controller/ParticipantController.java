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

import com.example.ODC_Academy.Dto.EmployeeDto;
import com.example.ODC_Academy.mapper.EmployeeMapper;
import com.example.ODC_Academy.model.Participant;
import com.example.ODC_Academy.service.ParticipantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    // Injection par constructeur
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    /**
     * Create - Ajoute un nouveau participant
     * @param Dto Le DTO contenant les informations du participant
     * @return Le DTO du participant sauvegardé avec un statut HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> createParticipant(@Valid @RequestBody EmployeeDto dto) {
        Participant participantToSave = Employeemapper.toEntity(Dto);
        Participant savedParticipant = participantService.saveParticipant(participantToSave);
        EmployeeDto responseDto = Employeemapper.toDTO(savedParticipant);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * Read - Récupère la liste de tous les participants
     * @return La liste des DTOs de participants
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllParticipants() {
        List<EmployeeDto> participants = participantService.getParticipants()
                .stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(participants);
    }

    /**
     * Read - Récupère un participant par son identifiant
     * @param id L'identifiant unique du participant
     * @return Le DTO du participant trouvé
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getParticipantById(@PathVariable("id") Long id) {
        Participant participant = participantService.getParticipant(id);
        return ResponseEntity.ok(EmployeeMapper.toDTO(participant));
    }

    /**
     * Update - Met à jour les informations d'un participant existant
     * @param id L'identifiant du participant à modifier
     * @param dto Les nouvelles informations
     * @return Le DTO mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateParticipant(
            @PathVariable("id") Long id,
            @Valid @RequestBody EmployeeDto dto) {
        Participant participantToUpdate = EmployeeMapper.toEntity(dto);
        Participant updatedParticipant = participantService.updateParticipant(id, participantToUpdate);
        return ResponseEntity.ok(EmployeeMapper.toDTO(updatedParticipant));
    }

    /**
     * Delete - Supprime un participant spécifique par son ID
     * @param id L'identifiant du participant
     * @return Statut HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipantById(@PathVariable("id") Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete All - Supprime tous les participants
     * @return Statut HTTP 204 No Content
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllParticipants() {
        participantService.deleteAllParticipants();
        return ResponseEntity.noContent().build();
    }
}