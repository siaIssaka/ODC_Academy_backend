package com.example.ODC_Academy.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.ODC_Academy.model.Participant;
import com.example.ODC_Academy.ParticipantRepository;

@Service 
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    // Injection par constructeur
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    /**
     * Récupère un participant par son ID ou lève une exception s'il n'existe pas
     */
    public Participant getParticipant(final Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant non trouvé avec l'id : " + id));
    }

    /**
     * Récupère la liste complète des participants
     */
    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    /**
     * Sauvegarde un nouveau participant
     */
    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    /**
     * Met à jour les données d'un participant existant
     */
    public Participant updateParticipant(final Long id, Participant participantDetails) {
        Participant existingParticipant = getParticipant(id);

        existingParticipant.setFirstName(participantDetails.getFirstName());
        existingParticipant.setLastName(participantDetails.getLastName());
        existingParticipant.setMail(participantDetails.getMail());
        
        if (participantDetails.getPassword() != null && !participantDetails.getPassword().isBlank()) {
            existingParticipant.setPassword(participantDetails.getPassword());
        }

        return participantRepository.save(existingParticipant);
    }

    /**
     * Supprime un participant par son ID
     */
    public void deleteParticipant(final Long id) {
        if (!participantRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : aucun participant trouvé avec l'id " + id);
        }
        participantRepository.deleteById(id);
    }

    /**
     * Supprime tous les participants de la base de données
     */
    public void deleteAllParticipants() {
        participantRepository.deleteAll();
    }
}