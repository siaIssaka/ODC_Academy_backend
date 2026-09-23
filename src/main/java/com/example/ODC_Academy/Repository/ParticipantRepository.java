package com.example.ODC_Academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ODC_Academy.model.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    // Recherche un participant par son identifiant ID (Hérité de JpaRepository)
    @Override
    Optional<Participant> findById(Long id);

    // Recherche un participant par son adresse email
    Optional<Participant> findByMail(String mail);

    // Vérifie si une adresse email existe déjà
    boolean existsByMail(String mail);

    // Retourne la liste complète des participants
    @Override
    List<Participant> findAll();
}