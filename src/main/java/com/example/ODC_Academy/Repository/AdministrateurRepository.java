package com.example.ODC_Academy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ODC_Academy.model.Administrateur;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

    @Override
    Optional<Administrateur> findById(Long id);

    Optional<Administrateur> findByMail(String mail);

    boolean existsByMail(String mail);

    @Override
    List<Administrateur> findAll();
}
