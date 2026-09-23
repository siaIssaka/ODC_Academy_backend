package com.example.ODC_Academy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ODC_Academy.model.Formateur;
import com.example.ODC_Academy.repository.FormateurRepository;

@Service
public class FormateurService {

    private final FormateurRepository formateurRepository;

    public FormateurService(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }

    public Formateur getFormateur(final Long id) {
        return formateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé avec l'id : " + id));
    }

    public List<Formateur> getFormateurs() {
        return formateurRepository.findAll();
    }

    public Formateur saveFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public Formateur updateFormateur(final Long id, Formateur formateurDetails) {
        Formateur existingFormateur = getFormateur(id);

        existingFormateur.setFirstName(formateurDetails.getFirstName());
        existingFormateur.setLastName(formateurDetails.getLastName());
        existingFormateur.setMail(formateurDetails.getMail());

        if (formateurDetails.getPassword() != null && !formateurDetails.getPassword().isBlank()) {
            existingFormateur.setPassword(formateurDetails.getPassword());
        }

        return formateurRepository.save(existingFormateur);
    }

    public void deleteFormateur(final Long id) {
        if (!formateurRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : aucun formateur trouvé avec l'id " + id);
        }
        formateurRepository.deleteById(id);
    }

    public void deleteAllFormateurs() {
        formateurRepository.deleteAll();
    }
}
