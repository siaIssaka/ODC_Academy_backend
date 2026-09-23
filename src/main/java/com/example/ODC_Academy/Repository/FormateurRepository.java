package com.example.ODC_Academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ODC_Academy.model.Formateur;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {

    @Override
    Optional<Formateur> findById(Long id);

    Optional<Formateur> findByMail(String mail);

    boolean existsByMail(String mail);

    @Override
    List<Formateur> findAll();
}
