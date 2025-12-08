package com.vettrack.appointment.infrastructure.adapter.out.persistence.repository;

import com.vettrack.appointment.infrastructure.adapter.out.persistence.entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaJpaRepository extends JpaRepository<CitaEntity, Long> {

    // Buscar citas por ID de mascota
    List<CitaEntity> findByMascotaId(Long mascotaId);

    // Buscar citas por ID de veterinario
    List<CitaEntity> findByVeterinarioId(Long veterinarioId);

    // Buscar citas por documento del dueño (usando JPQL)
    @Query("SELECT c FROM CitaEntity c WHERE c.mascota.documentoDueno = :documentoDueno")
    List<CitaEntity> findByDocumentoDueno(@Param("documentoDueno") String documentoDueno);
}