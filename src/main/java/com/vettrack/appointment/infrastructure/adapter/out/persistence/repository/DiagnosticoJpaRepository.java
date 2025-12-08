package com.vettrack.appointment.infrastructure.adapter.out.persistence.repository;

import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.DiagnosticoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticoJpaRepository extends JpaRepository<DiagnosticoEntity, Long> {

    // Métodos básicos heredados de JpaRepository son suficientes
}