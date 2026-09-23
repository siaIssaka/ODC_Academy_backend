package com.example.ODC_Academy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ODC_Academy.model.Administrateur;
import com.example.ODC_Academy.repository.AdministrateurRepository;

@Service
public class AdministrateurService {

    private final AdministrateurRepository administrateurRepository;

    public AdministrateurService(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;
    }

    public Administrateur getAdministrateur(final Long id) {
        return administrateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur non trouvé avec l'id : " + id));
    }

    public List<Administrateur> getAdministrateurs() {
        return administrateurRepository.findAll();
    }

    public Administrateur saveAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public Administrateur updateAdministrateur(final Long id, Administrateur administrateurDetails) {
        Administrateur existingAdministrateur = getAdministrateur(id);

        existingAdministrateur.setFirstName(administrateurDetails.getFirstName());
        existingAdministrateur.setLastName(administrateurDetails.getLastName());
        existingAdministrateur.setMail(administrateurDetails.getMail());

        if (administrateurDetails.getPassword() != null && !administrateurDetails.getPassword().isBlank()) {
            existingAdministrateur.setPassword(administrateurDetails.getPassword());
        }

        return administrateurRepository.save(existingAdministrateur);
    }

    public void deleteAdministrateur(final Long id) {
        if (!administrateurRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : aucun administrateur trouvé avec l'id " + id);
        }
        administrateurRepository.deleteById(id);
    }

    public void deleteAllAdministrateurs() {
        administrateurRepository.deleteAll();
    }
}
